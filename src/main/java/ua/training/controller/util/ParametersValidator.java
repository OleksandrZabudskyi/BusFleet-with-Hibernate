package ua.training.controller.util;

import org.apache.log4j.Logger;
import ua.training.constant.Attributes;
import ua.training.constant.GlobalConstants;
import ua.training.constant.Messages;
import ua.training.constant.Regex;
import ua.training.util.LocaleManager;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class for validating request parameters
 *
 * @author Zabudskyi Oleksandr
 * @see Regex
 */
public class ParametersValidator {
    private static Logger logger = Logger.getLogger(ParametersValidator.class);

    /**
     * Check if specified parameters in request is null or empty
     *
     * @param request HttpServletRequest request
     * @param parameters array of attributes constant parameter
     * @return boolean result is null or empty
     */
    public boolean validateIfNullOrEmpty(HttpServletRequest request, String... parameters) {
        boolean invalidParams = false;
        for (String parameterName : parameters) {
            String parameterValue = request.getParameter(parameterName);
            if (Objects.isNull(parameterValue) || parameterValue.trim().isEmpty()) {
                String errorName = String.format(LocaleManager.getProperty(Messages.PARAMS_ERROR), parameterName);
                String paramMessage = String.format(Messages.PARAMS_BLANK_FIELD, parameterValue);
                logger.debug(errorName.concat(GlobalConstants.COLON_SIGN).concat(paramMessage));
                invalidParams = true;
            }
        }
        return invalidParams;
    }

    /**
     * Validate all data for driver
     *
     * @param request HttpServletRequest request
     * @return true if any of parameters from request is not equal regex otherwise false
     */
    public boolean hasInvalidDriverData(HttpServletRequest request) {
        boolean result = false;
        Map<String, String> paramsToRegex = getPatternMapForDriver();
        Map<String, String[]> map = request.getParameterMap();

        if(map.size() == 0) {
            return true;
        }
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            if (paramsToRegex.containsKey(key) && isInvalidData(paramsToRegex.get(key), value)) {
                String errorName = String.format(Messages.PARAMS_ERROR, key);
                String paramMessage = String.format(Messages.WRONG_PARAMS, value);
                request.setAttribute(errorName, LocaleManager.getProperty(Messages.WRONG_PARAMS_DATA));
                logger.warn(errorName.concat(GlobalConstants.COLON_SIGN).concat(paramMessage));
                result = true;
            }
        }
        return result;
    }

    /**
     * Create map compliance parameter to regex
     *
     * @return map
     */
    private Map<String, String> getPatternMapForDriver() {
        Map<String, String> map = new HashMap<>();
        map.put(Attributes.FIRST_NAME, Regex.NAME);
        map.put(Attributes.LAST_NAME, Regex.NAME);
        map.put(Attributes.PHONE_NUMBER, Regex.PHONE);
        map.put(Attributes.DRIVER_LICENCE_NUMBER, Regex.LICENCE_NUMBER);
        map.put(Attributes.DRIVING_EXPERIENCE, Regex.POSITIVE_NUMBER);
        map.put(Attributes.EMAIL, Regex.REGEX_EMAIL);
        map.put(Attributes.PASSWORD, Regex.PASSWORD);
        return map;
    }

    /**
     *  Match value to regex
     *
     * @param regex pattern
     * @param parameterValue request parameter value
     * @return true if parameter value is not equal pattern otherwise false
     */
    private boolean isInvalidData(String regex, String parameterValue) {
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        return !pattern.matcher(parameterValue).matches();
    }
}
