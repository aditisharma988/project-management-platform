package com.project.authservice.utils;

import com.project.authservice.exception.ValidationError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@Slf4j
@RequiredArgsConstructor
public class ErrorUtils {

    private final ResourceBundleMessageSource source;

    public ValidationError getError(String fieldName, String fieldValue, String messageKey) {
        String errorMessage = source.getMessage(messageKey, new Object[]{}, getLocale());
        return new ValidationError(fieldName, fieldValue, errorMessage);
    }

    public ValidationError getError(String fieldName, String fieldValue, String messageKey, List<Object> params) {
        String errorMessage = source.getMessage(messageKey, params.toArray(), getLocale());
        return new ValidationError(fieldName, fieldValue, errorMessage);
    }

    public ValidationError getError(String messageKey, List<Object> params) {
        String errorMessage = source.getMessage(messageKey, params.toArray(), getLocale());
        return ValidationError.builder().message(errorMessage).build();
    }

    public String getErrorMessage(String messageKey) {
        return source.getMessage(messageKey, new Object[]{}, getLocale());
    }

    public String getErrorMessage(String messageKey, List<Object> params) {
        return source.getMessage(messageKey, params.toArray(), getLocale());
    }

    private @Nullable Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}