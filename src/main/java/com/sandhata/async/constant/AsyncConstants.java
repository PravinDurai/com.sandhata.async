package com.sandhata.async.constant;

public final class AsyncConstants {

	// Packages to Scan
	public static final String BASE_PACKAGE = "com.sandhata.async";

	// Headers
	public static final String X_TRANSACTION_ID = "X-Transaction-ID";
	public static final String X_ACTION = "X-Action";

	// Request Params
	public static final String ASYNC_PROCESSING = "async_processing";
	public static final String ASYNC_ANNOTATION = "async_annotation";
	public static final String REACTOR = "reactor";
	public static final String COUNT = "count";

	// General
	public static final Boolean TRUE = true;
	public static final Boolean FALSE = false;
	public static final String TRUE_STRING = "true";
	public static final String FALSE_STRING = "false";
	public static final String TRANSACTION_ID = "transactionId";
	public static final String DISABLED = "disabled";
	public static final String APPLY = "apply";
	public static final String SUCCESS = "Success";
	public static final String RESPONSE = "Response";
	public static final String FAILED = "failed";
	public static final String UNKNOWN = "Unknown";
	public static final String HIGH = "High";
	public static final String TECHNICAL = "Technical";
	public static final String BUSINESS = "Business";
	public static final String MODEL = "Model";
	public static final String SPACE = " ";
	public static final String CREATE = "create";
	public static final String UPDATE = "update";
	public static final String FIND = "find";
	public static final String FIND_ALL = "find-all";
	public static final String DELETE = "delete";
	public static final String PROPS = "props";
	public static final String ERROR_RESPONSE = "Error";
	public static final String INFO = "Info";
	public static final String DEBUG = "Debug";
	public static final String EXIT = "exit";

	// Formats
	public static final String SQL_DATE_FORMAT = "YYYY-MM-dd HH:mm:ss";

	// Kafka
	public static final String LOCAL_LICENSE_MANAGEMENT_TOPIC = "topic.async_processing";
	public static final String LOCAL_GROUP = "group.local";

	// Logging helpers
	public static final String REST = "Rest";
	public static final String SERVICE = "Service";
	public static final String BACKEND = "backend";
	public static final String KAFKA = "Kafka";
	public static final String REQUEST_IN = "request-in";
	public static final String REQUEST_OUT = "request-out";
	public static final String RESPONSE_IN = "response-in";
	public static final String RESPONSE_OUT = "response-out";
	public static final String TYPE = "Type";
	public static final String START = "Start";
	public static final String END = "End";

	// Single line logging
	public static final String OPERATION_LOG = "Operation :: {}";
	public static final String ERROR_LOG = "Error : \n {}";

	// Logging
	public static final String START_END = "Transaction ID : {} :: Class : {} :: Method : {} :: {}";
	public static final String OUT = "Transaction ID : {} :: Class : {} :: Method : {} :: {} : {} \n{}";
	public static final String ERROR = "Transaction Id : {} :: Caught in Exception :: Class : {} :: Method : {} :: Exception : \n{}";

	// Exception Handler
	public static final String ASYNC_EXCEPTION_HANDLER = "AsyncExceptionHandler";

	// Exception Handler Methods
	public static final String GENERIC_EXCEPTION_HANDLER = "genericExceptionHandler";
	public static final String ASYNC_CUSTOME_EXCEPTION_HANDLER = "asyncCustomExceptionHandler";

	// Rest
	public static final String ASYNC_CONTROLLER = "AsyncController";

	// Service
	public static final String ASYNC_SERVICE = "AsyncService";
	public static final String REACTOR_SERVICE = "ReactorService";

	// Backend Service
	public static final String ASYNC_BAK = "AsyncBak";
	public static final String KAFKA_PRODUCER_SERVICE = "KafkaProducerService";

	// Methods
	// AsyncController
	public static final String GET_ALL_EMPLOYEE_INFO_SYNC_REST = "getAllEmployeeInfoSyncRest";
	public static final String GET_ALL_EMPLOYEE_INFO_REST = "getAllEmployeeInfoRest";
	public static final String GET_ALL_EMPLOYEE_INFO_REACTOR = "getAllEmployeeInfoReactor";
	public static final String GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_REST = "getAllEmployeeInfoUsingExecutorRest";
	public static final String GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_REST="getAllEmployeeInfoUsingExecutorSupplyAsyncRest";

	// AsyncService|ReactorService
	public static final String GET_ALL_EMPLOYEE_INFO_SYNC_SERVICE = "getAllEmployeeInfoSyncService";
	public static final String GET_ALL_EMPLOYEE_INFO_SERVICE = "getAllEmployeeInfoService";
	public static final String GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SERVICE = "getAllEmployeeInfoUsingExecutorService";
	public static final String GET_ALL_EMPLOYEE_INFO_USING_EXECUTOR_SUPPLY_ASYNC_SERVICE = "getAllEmployeeInfoUsingExecutorSupplyAsyncService";

	// AsyncBak
	public static final String RETRIEVE_SYNC_RESPONSE = "retrieveSyncResponse";
	public static final String GET_ALL_USER_INFO_ASYNC_BAK = "getAllUserInfoAsyncBak";

	// Kafka Producer Service
	public static final String PUSH_MESSAGE_TO_KAFKA = "pushMessageToKafka";

	// Http Status Codes
	public static final String STATUS_200 = "200";
	public static final String STATUS_202 = "202";
	public static final String STATUS_400 = "400";
	public static final String STATUS_404 = "404";
	public static final String STATUS_500 = "500";

}
