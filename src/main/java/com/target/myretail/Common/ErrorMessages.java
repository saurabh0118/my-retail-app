package com.target.myretail.Common;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessages {
	
	public static String getErrorMessage(String errCode) {
		
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("400","404 - Bad Request");
		errorMap.put("401","401 - Unauthorized (RFC 7235)");
		errorMap.put("402","402 - Payment Required");
		errorMap.put("403","403 - Forbidden");
		errorMap.put("404","404 - Not Found");
		errorMap.put("405","405 - Method Not Allowed");
		errorMap.put("406","406 - Not Acceptable");
		errorMap.put("407","407 - Proxy Authentication Required (RFC 7235)");
		errorMap.put("408","408 - Request Timeout");
		errorMap.put("409","409 - Conflict");
		errorMap.put("410","410 - Gone");
		errorMap.put("411","411 - Length Required");
		errorMap.put("412","412 - Precondition Failed (RFC 7232)");
		errorMap.put("413","413 - Payload Too Large (RFC 7231)");
		errorMap.put("414","414 - URI Too Long (RFC 7231)");
		errorMap.put("415","415 - Unsupported Media Type");
		errorMap.put("416","416 - Range Not Satisfiable (RFC 7233)");
		errorMap.put("417","417 - Expectation Failed");
		errorMap.put("418","418 - I'm a teapot (RFC 2324)");
		errorMap.put("421","421 - Misdirected Request (RFC 7540)");
		errorMap.put("422","422 - Unprocessable Entity (WebDAV; RFC 4918)");
		errorMap.put("423","423 - Locked (WebDAV; RFC 4918)");
		errorMap.put("424","424 - Failed Dependency (WebDAV; RFC 4918)");
		errorMap.put("426","426 - Upgrade Required");
		errorMap.put("428","428 - Precondition Required (RFC 6585)");
		errorMap.put("429","429 - Too Many Requests (RFC 6585)");
		errorMap.put("431","431 - Request Header Fields Too Large (RFC 6585)");
		errorMap.put("451","432 - Unavailable For Legal Reasons (RFC 7725)");
		errorMap.put("500","500 - Internal Server Error");
		errorMap.put("501","501 - Not Implemented");
		errorMap.put("502","502 - Bad Gateway");
		errorMap.put("503","503 - Service Unavailable");
		errorMap.put("504","504 - Gateway Timeout");
		errorMap.put("505","505 - HTTP Version Not Supported");
		errorMap.put("506","506 - Variant Also Negotiates (RFC 2295)");
		errorMap.put("507","507 - Insufficient Storage (WebDAV; RFC 4918)");
		errorMap.put("508","508 - Loop Detected (WebDAV; RFC 5842)");
		errorMap.put("510","510 - Not Extended (RFC 2774)");
		errorMap.put("511","511 - Network Authentication Required (RFC 6585)");	
		
		String errorMessage = "Error Occured";
	    
		if (errCode.contains("400")){
			errorMessage = errorMap.get("400");
			}
		if (errCode.contains("401")){
			errorMessage = errorMap.get("401");
			}
		if (errCode.contains("402")){
			errorMessage = errorMap.get("402");
			}
		if (errCode.contains("403")){
			errorMessage = errorMap.get("403");
			}
		if (errCode.contains("404")){
			errorMessage = errorMap.get("404");
			}
		if (errCode.contains("405")){
			errorMessage = errorMap.get("405");
			}
		if (errCode.contains("406")){
			errorMessage = errorMap.get("406");
			}
		if (errCode.contains("407")){
			errorMessage = errorMap.get("407");
			}
		if (errCode.contains("408")){
			errorMessage = errorMap.get("408");
			}
		if (errCode.contains("409")){
			errorMessage = errorMap.get("409");
			}
		if (errCode.contains("410")){
			errorMessage = errorMap.get("410");
			}
		if (errCode.contains("411")){
			errorMessage = errorMap.get("411");
			}
		if (errCode.contains("412")){
			errorMessage = errorMap.get("412");
			}
		if (errCode.contains("413")){
			errorMessage = errorMap.get("413");
			}
		if (errCode.contains("414")){
			errorMessage = errorMap.get("414");
			}
		if (errCode.contains("415")){
			errorMessage = errorMap.get("415");
			}
		if (errCode.contains("416")){
			errorMessage = errorMap.get("416");
			}
		if (errCode.contains("417")){
			errorMessage = errorMap.get("417");
			}
		if (errCode.contains("418")){
			errorMessage = errorMap.get("418");
			}
		if (errCode.contains("421")){
			errorMessage = errorMap.get("421");
			}
		if (errCode.contains("422")){
			errorMessage = errorMap.get("422");
			}
		if (errCode.contains("423")){
			errorMessage = errorMap.get("423");
			}
		if (errCode.contains("424")){
			errorMessage = errorMap.get("424");
			}
		if (errCode.contains("426")){
			errorMessage = errorMap.get("426");
			}
		if (errCode.contains("428")){
			errorMessage = errorMap.get("428");
			}
		if (errCode.contains("429")){
			errorMessage = errorMap.get("429");
			}
		if (errCode.contains("431")){
			errorMessage = errorMap.get("431");
			}
		if (errCode.contains("451")){
			errorMessage = errorMap.get("451");
			}
		if (errCode.contains("500")){
			errorMessage = errorMap.get("500");
			}
		if (errCode.contains("501")){
			errorMessage = errorMap.get("501");
			}
		if (errCode.contains("502")){
			errorMessage = errorMap.get("502");
			}
		if (errCode.contains("503")){
			errorMessage = errorMap.get("503");
			}
		if (errCode.contains("504")){
			errorMessage = errorMap.get("504");
			}
		if (errCode.contains("505")){
			errorMessage = errorMap.get("505");
			}
		if (errCode.contains("506")){
			errorMessage = errorMap.get("506");
			}
		if (errCode.contains("507")){
			errorMessage = errorMap.get("507");
			}
		if (errCode.contains("508")){
			errorMessage = errorMap.get("508");
			}
		if (errCode.contains("510")){
			errorMessage = errorMap.get("510");
			}
		if (errCode.contains("511")){
			errorMessage = errorMap.get("511");
			}
         
		return errorMessage;
		
	}

}
