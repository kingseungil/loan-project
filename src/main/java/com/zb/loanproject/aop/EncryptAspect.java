package com.zb.loanproject.aop;

import com.zb.loanproject.service.EncryptService;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.BiFunction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class EncryptAspect {

    private final EncryptService encryptService;

    // save()
    @Pointcut("execution(* com.zb.loanproject.repository.UserRepository.save(..))")
    public void save() {
    }

    // find()
    @Pointcut("execution(* com.zb.loanproject.repository.UserRepository.find*(..))")
    public void find() {
    }

    @Before("save()")
    public void encryptOnSave(JoinPoint joinPoint) throws IllegalAccessException {
        Object entity = joinPoint.getArgs()[0];
        processFields(entity, (value, field) -> {
            try {
                return encryptService.encrypt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @AfterReturning(pointcut = "find()", returning = "entity")
    public void decryptOnFind(JoinPoint joinPoint, Object entity) throws IllegalAccessException {
        if (entity instanceof Optional<?> optionalEntity) {
            if (optionalEntity.isPresent()) {
                Object actualEntity = optionalEntity.get();
                processFields(actualEntity, (value, field) -> {
                    try {
                        return encryptService.decrypt(value);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                // Optional이 비어있음을 처리
                throw new RuntimeException("aop - decryptOnFind - entity is empty");
            }
        } else {
            // entity가 Optional이 아닌 경우를 처리
            throw new RuntimeException("aop - decryptOnFind - entity is not optional");
        }
    }

    private void processFields(Object entity, BiFunction<String, Field, String> function)
      throws IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            Encrypt enc = field.getAnnotation(Encrypt.class);
            if (enc != null) {
                field.setAccessible(true);
                String value = (String) field.get(entity);
                String processedValue = function.apply(value, field);
                field.set(entity, processedValue);
            }
        }
    }

}
