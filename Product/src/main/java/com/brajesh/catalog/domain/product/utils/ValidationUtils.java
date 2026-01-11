package com.brajesh.catalog.domain.product.utils;

import com.brajesh.catalog.domain.product.exception.ProductException;
import com.brajesh.catalog.domain.product.model.api.requestDto.ProductRequestDto;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.brajesh.catalog.domain.product.exception.ProductDetailsException.SB_PL_INVALID_INPUT_DATA;
import static org.slf4j.LoggerFactory.getLogger;

@Component
@NoArgsConstructor
public class ValidationUtils {

    private static final Logger logger= getLogger(ValidationUtils.class);

    public static Mono<List<ProductRequestDto>> validateAndReturnList(
            List<ProductRequestDto> list
    ) {
        return Flux.fromIterable(list)
                .filter(p -> p.getProductId() == null || p.getProductId().isBlank())
                .hasElements()
                .flatMap(hasInvalid -> {
                    if (hasInvalid) {
                        return Mono.error(toDxpExceptionWhenInValidInputData());
                    }
                    return Mono.just(list);
                });
    }


   public static boolean isMissingOrValidProductId(String productId){
        if (productId==null ||productId.isBlank()){
            throw new ProductException(SB_PL_INVALID_INPUT_DATA);
        }
       return true;
   }

    private static RuntimeException toDxpExceptionWhenInValidInputData() {
        return new ProductException(SB_PL_INVALID_INPUT_DATA);
    }



}
