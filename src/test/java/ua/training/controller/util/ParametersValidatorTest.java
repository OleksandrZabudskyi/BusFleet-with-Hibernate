package ua.training.controller.util;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.training.constant.Attributes;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParametersValidatorTest {
    @Mock
    private HttpServletRequest request;
    private ParametersValidator parametersValidator;
    private String [] parameters;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        parametersValidator = new ParametersValidator();
        parameters = new String[]{Attributes.EMAIL, Attributes.PASSWORD, Attributes.TRIP_ID};
    }

    @Test
    public void testIfRequestParamIsNull() {
        when(request.getParameter(anyString())).thenReturn(null);
        boolean result = parametersValidator.validateIfNullOrEmpty(request, parameters);
        assertTrue(result);
        verify(request).getParameter(Attributes.EMAIL);
    }

    @Test
    public void testIfRequestParamIsEmpty() {
        when(request.getParameter(anyString())).thenReturn("");
        boolean result = parametersValidator.validateIfNullOrEmpty(request, parameters);
        assertTrue(result);
        verify(request).getParameter(Attributes.EMAIL);
    }

    @Test
    public void testIfRequestHasValidParameter() {
        when(request.getParameter(anyString())).thenReturn("string");
        boolean result = parametersValidator.validateIfNullOrEmpty(request, parameters);
        assertFalse(result);
        verify(request).getParameter(Attributes.EMAIL);
    }

    @Test
    public void testIfAllDriverParametersAreValid() {
        Map<String, String[]> map = new HashMap<>();
        map.put(Attributes.FIRST_NAME, new String[] {"Viktor"});
        map.put(Attributes.LAST_NAME, new String[] {"Gorin"});
        map.put(Attributes.PHONE_NUMBER, new String[] {"+38(067)243-12-34"});
        map.put(Attributes.DRIVER_LICENCE_NUMBER, new String[] {"XBN23456"});
        map.put(Attributes.DRIVING_EXPERIENCE, new String[] {"12"});
        map.put(Attributes.EMAIL, new String[] {"gorin@gmail.com"});
        map.put(Attributes.PASSWORD, new String[] {"12345"});
        when(request.getParameterMap()).thenReturn(map);
        boolean result = parametersValidator.hasInvalidDriverData(request);
        assertFalse(result);
        verify(request).getParameterMap();
    }

    @Test
    public void testIfHasInvalidDriverPhoneNumber() {
        Map<String, String[]> map = new HashMap<>();
        map.put(Attributes.PHONE_NUMBER, new String[] {"+38067243-12-34"});
        when(request.getParameterMap()).thenReturn(map);
        boolean result = parametersValidator.hasInvalidDriverData(request);
        assertTrue(result);
        verify(request).getParameterMap();
    }
}