package org.hsqldb.analysis;

/**
 * This maps SQL Commands onto the HSQLDB StatementTypes
 * object, which is just a constants file containing 
 * every statement that can be interpreted by HSQLDB.  
 * 
 * The original names are preserved here as comments
 * so it's easier to figure out what you're looking at.
 * 
 * @author DeepBlue
 *
 */
public class StatementTypeClassifier 
{
	
	public static SQLCommand classifyStatementTypeAsCommand(int value)
	{
		switch(value){
		case 1: // ALLOCATE_CURSOR    
			return SQLCommand.INSERT;
		case 2: // ALLOCATE_DESCRIPTOR
			return SQLCommand.UNKNOWN;
		case 3: // ALTER_DOMAIN
			return SQLCommand.UNKNOWN;
		case 17: // ALTER_ROUTINE
			return SQLCommand.UNKNOWN;
		case 134: // ALTER_SEQUENCE
			return SQLCommand.UNKNOWN;
		case 60: // ALTER_TYPE
			return SQLCommand.UNKNOWN;
		case 4:// ALTER_TABLE         
			return SQLCommand.UNKNOWN;
		case 127:// ALTER_TRANSFORM   
			return SQLCommand.UNKNOWN;
		case 6:// CREATE_ASSERTION    
			return SQLCommand.UNKNOWN;
		case 7:// CALL                
			return SQLCommand.UNKNOWN;
		case 8: // CREATE_CHARACTER_SET
			return SQLCommand.UNKNOWN;
		case 9: // CLOSE_CURSOR       
			return SQLCommand.UNKNOWN;
		case 10: // CREATE_COLLATION  
			return SQLCommand.UNKNOWN;
		case 11: // COMMIT_WORK       
			return SQLCommand.UNKNOWN;
		case 13: // CONNECT           
			return SQLCommand.UNKNOWN;
		case 15: // DEALLOCATE_DESCRIPTOR         
			return SQLCommand.UNKNOWN;
		case 16: // DEALLOCATE_PREPARE
			return SQLCommand.UNKNOWN;
		case 18: // DELETE_CURSOR     
			return SQLCommand.UNKNOWN;
		case 19: // DELETE_WHERE      
			return SQLCommand.DELETE;
		case 20: // DESCRIBE          
			return SQLCommand.UNKNOWN;
		case 21: // SELECT_DIRECT_SINGLE
			return SQLCommand.UNKNOWN;
		case 22: // DISCONNECT        
			return SQLCommand.UNKNOWN;
		case 23: // CREATE_DOMAIN     
			return SQLCommand.UNKNOWN;
		case 24: // DROP_ASSERTION    
			return SQLCommand.UNKNOWN;
		case 25: // DROP_CHARACTER_SET
			return SQLCommand.UNKNOWN;
		case 26: // DROP_COLLATION    
			return SQLCommand.UNKNOWN;
		case 35:// DROP_TYPE          
			return SQLCommand.UNKNOWN;
		case 27:// DROP_DOMAIN        
			return SQLCommand.UNKNOWN;
		case 29:// DROP_ROLE          
			return SQLCommand.UNKNOWN;
		case 30:// DROP_ROUTINE       
			return SQLCommand.UNKNOWN;
		case 31:// DROP_SCHEMA        
			return SQLCommand.UNKNOWN;
		case 135:// DROP_SEQUENCE     
			return SQLCommand.UNKNOWN;
		case 32:// DROP_TABLE         
			return SQLCommand.ALTER;
		case 116:// DROP_TRANSFORM    
			return SQLCommand.UNKNOWN;
		case 33:// DROP_TRANSLATION   
			return SQLCommand.UNKNOWN;
		 case 34:// DROP_TRIGGER      
			 return SQLCommand.UNKNOWN;
		 case 78:// DROP_CAST          
			 return SQLCommand.UNKNOWN;
		 case 115:// DROP_ORDERING     
			 return SQLCommand.UNKNOWN;
		 case 36:// DROP_VIEW          
			 return SQLCommand.UNKNOWN;
		 case 37:// DYNAMIC_CLOSE      
			 return SQLCommand.UNKNOWN;
		 case 38:// DYNAMIC_DELETE_CURSOR
			 return SQLCommand.UNKNOWN;
		 case 39:// DYNAMIC_FETCH      
			 return SQLCommand.UNKNOWN;
		 case 40:// DYNAMIC_OPEN       
			 return SQLCommand.UNKNOWN;
		 case 85:// SELECT_CURSOR      
			 return SQLCommand.SELECT;
		 case 41:// SELECT_SINGLE_DYNAMIC               
			 return SQLCommand.UNKNOWN;
		 case 42:// DYNAMIC_UPDATE_CURSOR
			 return SQLCommand.UNKNOWN;
		 case 43:// EXECUTE_IMMEDIATE  
			 return SQLCommand.UNKNOWN;
		 case 44:// EXECUTE            
			 return SQLCommand.UNKNOWN;
		 case 45:// FETCH              
			 return SQLCommand.UNKNOWN;
		 case 98:// FREE_LOCATOR       
			 return SQLCommand.UNKNOWN;
		 case 47:// GET_DESCRIPTOR     
			 return SQLCommand.UNKNOWN;
		 case 99:// HOLD_LOCATOR       
			 return SQLCommand.UNKNOWN;
		 case 48:// GRANT              
			 return SQLCommand.UNKNOWN;
		 case 49:// GRANT_ROLE         
			 return SQLCommand.UNKNOWN;
		 case 50:// INSERT             
			 return SQLCommand.INSERT;
		 case 128:// MERGE             
			 return SQLCommand.UPDATE;
		 case 53:// OPEN               
			 return SQLCommand.UNKNOWN;
		 case 54:// PREPARABLE_DYNAMIC_DELETE_CURSOR
			 return SQLCommand.UNKNOWN;
		 case 55:// PREPARABLE_DYNAMIC_UPDATE_CURSOR
			 return SQLCommand.UNKNOWN;
		 case 56:// PREPARE            
			 return SQLCommand.UNKNOWN;
		 case 57:// RELEASE_SAVEPO//   
			 return SQLCommand.UNKNOWN;
		 case 58:// RETURN             
			 return SQLCommand.UNKNOWN;
		 case 59:// REVOKE             
			 return SQLCommand.UNKNOWN;
		 case 129:// REVOKE_ROLE       
			 return SQLCommand.UNKNOWN;
		 case 61:// CREATE_ROLE        
			 return SQLCommand.UNKNOWN;
		 case 62:// ROLLBACK_WORK      
			 return SQLCommand.UNKNOWN;
		 case 63:// SAVEPO//           
			 return SQLCommand.UNKNOWN;
		 case 64:// CREATE_SCHEMA      
			 return SQLCommand.UNKNOWN;
		 case 14:// CREATE_ROUTINE     
			 return SQLCommand.UNKNOWN;
		 case 65:// SELECT_SINGLE      
			 return SQLCommand.UNKNOWN;
		 case 133:// CREATE_SEQUENCE   
			 return SQLCommand.UNKNOWN;
		 case 66:// SET_CATALOG        
			 return SQLCommand.UNKNOWN;
		 case 67:// SET_CONNECTION     
			 return SQLCommand.UNKNOWN;
		 case 68:// SET_CONSTRA//      
			 return SQLCommand.UNKNOWN;
		 case 70:// SET_DESCRIPTOR     
			 return SQLCommand.UNKNOWN;
		 case 71:// SET_TIME_ZONE      
			 return SQLCommand.UNKNOWN;
		 case 72:// SET_NAMES          
			 return SQLCommand.UNKNOWN;
		 case 69:// SET_PATH           
			 return SQLCommand.UNKNOWN;
		 case 73:// SET_ROLE           
			 return SQLCommand.UNKNOWN;
		 case 74:// SET_SCHEMA         
			 return SQLCommand.UNKNOWN;
		 case 76:// SET_SESSION_AUTHORIZATION
			 return SQLCommand.UNKNOWN;
		 case 109:// SET_SESSION_CHARACTERISTICS
			 return SQLCommand.UNKNOWN;
		 case 136:// SET_COLLATION     
			 return SQLCommand.UNKNOWN;
		 case 118:// SET_TRANSFORM_GROUP
			 return SQLCommand.UNKNOWN;
		 case 75:// SET_TRANSACTION    
			 return SQLCommand.UNKNOWN;
		 case 111:// START_TRANSACTION 
			 return SQLCommand.UNKNOWN;
		 case 77:// CREATE_TABLE       
			 return SQLCommand.UNKNOWN;
		 case 117:// CREATE_TRANSFORM  
			 return SQLCommand.UNKNOWN;
		 case 79:// CREATE_TRANSLATION 
			 return SQLCommand.UNKNOWN;
		 case 80:// CREATE_TRIGGER     
			 return SQLCommand.UNKNOWN;
		 case 81:// UPDATE_CURSOR      
			 return SQLCommand.UPDATE;
		 case 82:// UPDATE_WHERE       
			 return SQLCommand.UPDATE;
		 case 52:// CREATE_CAST        
			 return SQLCommand.UNKNOWN;
		 case 83:// CREATE_TYPE        
			 return SQLCommand.UNKNOWN;
		 case 114:// CREATE_ORDERING   
			 return SQLCommand.UNKNOWN;
		 case 84:// CREATE_VIEW        
			 return SQLCommand.UNKNOWN;
		 case 5:// ASSIGNMENT          
			 return SQLCommand.UNKNOWN;
		 case 86:// CASE               
			 return SQLCommand.UNKNOWN;
		 case 12:// BEGIN_END          
			 return SQLCommand.UNKNOWN;
		 case 28:// DROP_MODULE        
			 return SQLCommand.UNKNOWN;
		 case 46:// FOR                
			 return SQLCommand.UNKNOWN;
		 case 88:// IF                 
			 return SQLCommand.UNKNOWN;
		 case 102:// ITERATE           
			 return SQLCommand.UNKNOWN;
		 case 89:// LEAVE              
			 return SQLCommand.UNKNOWN;
		 case 90:// LOOP               
			 return SQLCommand.UNKNOWN;
		 case 91:// RESIGNAL           
			 return SQLCommand.UNKNOWN;
		 case 95:// REPEAT             
			 return SQLCommand.UNKNOWN;
		 case 92: // SIGNAL            
			 return SQLCommand.UNKNOWN;
		 case 51:// CREATE_MODULE      
			 return SQLCommand.UNKNOWN;
		 case 97:// WHILE              
			 return SQLCommand.UNKNOWN;
		 case 104:// ALTER_FOREIGN_TABLE         
			 return SQLCommand.UNKNOWN;
		 case 123:// ALTER_USER_MAPPING
			 return SQLCommand.UNKNOWN;
		 case 121:// DROP_FOREIGN_DATA_WRAPPER
			 return SQLCommand.UNKNOWN;
		 case 110:// DROP_SERVER       
			 return SQLCommand.UNKNOWN;
		 case 105:// DROP_FOREIGN_TABLE
			 return SQLCommand.UNKNOWN;
		 case 131:// DROP_ROUTINE_MAPPING 
			 return SQLCommand.UNKNOWN;
		 case 124:// DROP_USER_MAPPING 
			 return SQLCommand.UNKNOWN;
		 case 119:// CREATE_FOREIGN_DATA_WRAPPER
			 return SQLCommand.UNKNOWN;
		 case 107:// CREATE_SERVER     
			 return SQLCommand.UNKNOWN;
		 case 103:// CREATE_FOREIGN_TABLE
			 return SQLCommand.UNKNOWN;
		 case 125: // IMPORT_FOREIGN_SCHEMA
			 return SQLCommand.UNKNOWN;
		 case 132:// CREATE_ROUTINE_MAPPING
			 return SQLCommand.UNKNOWN;
		 case 126:// SET_PASSTHROUGH   
			 return SQLCommand.UNKNOWN;
		 case 122:// CREATE_USER_MAPPING
			 return SQLCommand.UNKNOWN;

	    // hsqldb database
		 case 1001:// DATABASE_BACKUP     
			 return SQLCommand.UNKNOWN;
		 case 1002:// DATABASE_CHECKPO//
			 return SQLCommand.UNKNOWN;
		 case 1003:// DATABASE_SHUTDOWN
			 return SQLCommand.UNKNOWN;
		 case 1004:// DATABASE_SCRIPT  
			 return SQLCommand.UNKNOWN;
		 case 1005:// ALTER_SESSION    
			 return SQLCommand.UNKNOWN;

	    // hsqldb database settings
		 case 1011:// SET_DATABASE_FILES_BACKUP_INCREMENT    
			 return SQLCommand.UNKNOWN;
		 case 1012:// SET_DATABASE_FILES_CACHE_ROWS
			 return SQLCommand.UNKNOWN;
		 case 1013:// SET_DATABASE_FILES_CACHE_SIZE
			 return SQLCommand.UNKNOWN;
		 case 1014:// SET_DATABASE_FILES_DEFRAG
			 return SQLCommand.UNKNOWN;
		 case 1015:// SET_DATABASE_FILES_EVENT_LOG
			 return SQLCommand.UNKNOWN;
		 case 1016:// SET_DATABASE_FILES_LOBS_SCALE 
			 return SQLCommand.UNKNOWN;
		 case 1017:// SET_DATABASE_FILES_UNUSED_TYPE_SETTING 
			 return SQLCommand.UNKNOWN;
		 case 1018:// SET_DATABASE_FILES_LOG      
			 return SQLCommand.UNKNOWN;
		 case 1019:// SET_DATABASE_FILES_LOG_SIZE
			 return SQLCommand.UNKNOWN;
		 case 1020:// SET_DATABASE_FILES_NIO
			 return SQLCommand.UNKNOWN;
		 case 1021:// SET_DATABASE_FILES_READ_ONLY 
			 return SQLCommand.UNKNOWN;
		 case 1022:// SET_DATABASE_FILES_READ_ONLY_FILES
			 return SQLCommand.UNKNOWN;
		 case 1023:// SET_DATABASE_FILES_SCALE
			 return SQLCommand.UNKNOWN;
		 case 1024:// SET_DATABASE_FILES_SCRIPT_FORMAT
			 return SQLCommand.UNKNOWN;
		 case 1025:// SET_DATABASE_FILES_TEMP_PATH     
			 return SQLCommand.UNKNOWN;
		 case 1026:// SET_DATABASE_FILES_WRITE_DELAY
			 return SQLCommand.UNKNOWN;
		 case 1031:// SET_DATABASE_DEFAULT_INITIAL_SCHEMA
			 return SQLCommand.UNKNOWN;
		 case 1032:// SET_DATABASE_DEFAULT_TABLE_TYPE
			 return SQLCommand.UNKNOWN;
		 case 1033:// SET_DATABASE_AUTHENTICATION 
			 return SQLCommand.UNKNOWN;
		 case 1034:// SET_DATABASE_GC  
			 return SQLCommand.UNKNOWN;
		 case 1035:// SET_DATABASE_PROPERTY
			 return SQLCommand.UNKNOWN;
		 case 1036:// SET_DATABASE_PASSWORD_CHECK    
			 return SQLCommand.UNKNOWN;
		 case 1037:// SET_DATABASE_READ_ONLY
			 return SQLCommand.UNKNOWN;
		 case 1039:// SET_DATABASE_READ_ONLY_FILES
			 return SQLCommand.UNKNOWN;
		 case 1040:// SET_DATABASE_RESULT_MEMORY_ROWS
			 return SQLCommand.UNKNOWN;
		 case 1041:// SET_DATABASE_SQL_COLLATION
			 return SQLCommand.UNKNOWN;
		 case 1042:// SET_SESSION_SQL_IGNORECASE
			 return SQLCommand.UNKNOWN;
		 case 1046:// SET_DATABASE_SQL_REFERENTIAL_//EGRITY 
			 return SQLCommand.UNKNOWN;
		 case 1047:// SET_DATABASE_SQL 
			 return SQLCommand.UNKNOWN;
		 case 1048:// SET_DATABASE_TEXT_SOURCE
			 return SQLCommand.UNKNOWN;
		 case 1049:// SET_DATABASE_TRANSACTION_CONTROL
			 return SQLCommand.UNKNOWN;
		 case 1050:// SET_DATABASE_DEFAULT_ISOLATION_LEVEL
			 return SQLCommand.UNKNOWN;
		 case 1051:// SET_DATABASE_TRANSACTION_CONFLICT 
			 return SQLCommand.UNKNOWN;
		 case 1052:// SET_DATABASE_UNIQUE_NAME
			 return SQLCommand.UNKNOWN;

	    // hsqldb user settings
		 case 1060:// SET_USER_LOCAL     
			 return SQLCommand.UNKNOWN;
		 case 1061:// SET_USER_INITIAL_SCHEMA 
			 return SQLCommand.UNKNOWN;
		 case 1062:// SET_USER_PASSWORD   
			 return SQLCommand.UNKNOWN;

	    // hsqldb session
		 case 1063:// TRANSACTION_LOCK_TABLE  
			 return SQLCommand.UNKNOWN;
		 case 1064:// SET_SESSION_AUTOCOMMIT
			 return SQLCommand.UNKNOWN;
		 case 1065:// SET_SESSION_RESULT_MAX_ROWS    
			 return SQLCommand.UNKNOWN;
		 case 1066:// SET_SESSION_RESULT_MEMORY_ROWS 
			 return SQLCommand.UNKNOWN;
		 case 1067:// ROLLBACK_SAVEPO//   
			 return SQLCommand.UNKNOWN;
		 case 1068:// DECLARE_SESSION_TABLE 
			 return SQLCommand.UNKNOWN;

	    // hsqldb schema
		 case 1069:// ALTER_INDEX     
			 return SQLCommand.ALTER;
		 case 1070:// ALTER_VIEW   
			 return SQLCommand.ALTER;
		 case 1071:// COMMENT    
			 return SQLCommand.ALTER;
		 case 1072:// CREATE_ALIAS  
			 return SQLCommand.ALTER;
		 case 1073:// CREATE_INDEX   
			 return SQLCommand.ALTER;
		 case 1074:// CREATE_USER   
			 return SQLCommand.ALTER;
		 case 1075:// DECLARE_VARIABLE    
			 return SQLCommand.ALTER;
		 case 1076:// DROP_COLUMN  
			 return SQLCommand.ALTER;
		 case 1077:// DROP_INDEX   
			 return SQLCommand.ALTER;
		 case 1078:// DROP_CONSTRA//   
			 return SQLCommand.ALTER;
		 case 1079:// DROP_USER     
			 return SQLCommand.ALTER;
		 case 1080:// DROP_DEFAULT    
			 return SQLCommand.ALTER;
		 case 1081:// ADD_COLUMN   
			 return SQLCommand.ALTER;
		 case 1082:// ADD_CONSTRA//   
			 return SQLCommand.ALTER;
		 case 1083:// ADD_DEFAULT   
			 return SQLCommand.ALTER;
		 case 1084:// ALTER_COLUMN_TYPE    
			 return SQLCommand.ALTER;
		 case 1085:// ALTER_COLUMN_SEQUENCE     
			 return SQLCommand.ALTER;
		 case 1086: // ALTER_COLUMN_NULL 
			 return SQLCommand.ALTER;
		 case 1087:// ALTER_COLUMN_DEFAULT    
			 return SQLCommand.ALTER;
		 case 1088:// ALTER_COLUMN_DROP_DEFAULT   
			 return SQLCommand.ALTER;
		 case 1089:// ALTER_COLUMN_DROP_GENERATED 
			 return SQLCommand.ALTER;
		 case 1090:// ALTER_COLUMN_TYPE_IDENTITY  
			 return SQLCommand.ALTER;

	    //
		 case 1191:// EXPLAIN_PLAN   
			 return SQLCommand.UNKNOWN;
		 case 1192:// RENAME_OBJECT   
			 return SQLCommand.UNKNOWN;
		 case 1193:// SET_TABLE_INDEX     
			 return SQLCommand.UNKNOWN;
		 case 1194:// SET_TABLE_READONLY      
			 return SQLCommand.UNKNOWN;
		 case 1195:// SET_TABLE_SOURCE        
			 return SQLCommand.UNKNOWN;
		 case 1196:// SET_TABLE_SOURCE_HEADER 
			 return SQLCommand.UNKNOWN;
		 case 1197:// SET_TABLE_TYPE        
			 return SQLCommand.UNKNOWN;
		 case 1198:// SET_TABLE_CLUSTERED     
			 return SQLCommand.UNKNOWN;
		 case 1199:// LOG_SCHEMA_STATEMENT    
			 return SQLCommand.UNKNOWN;

	    // hsqldb sql implementation
		 case 1201:// CONDITION       
			 return SQLCommand.UNKNOWN;// element of IF
		 case 1202:// HANDLER   
			 return SQLCommand.UNKNOWN;
		 case 1203:// DDL      
			 return SQLCommand.UNKNOWN;
		 case 1204:// CHECK   
			 return SQLCommand.UNKNOWN;
		 case 1205:// TRUNCATE  
			 return SQLCommand.DELETE;
		}
		
		throw new RuntimeException("Somehow bottomed out of the decision switch.");
	}
	
//	public static SQLCommand classifyStatementTypeAsCommand(// value)
//	{
//		switch(value)
//		{
//			case 1:
//				return SQLCommand.INSERT:
//			case 19:
//				return SQLCommand.DELETE:
//			case 1205:
//				return SQLCommand.DELETE:
//			case 82:
//				return SQLCommand.UPDATE:
//			case 128:
//				return SQLCommand.UPDATE:
//			case 85:
//				return SQLCommand.SELECT:
//		}
//		
//		throw new RuntimeException("Unclassified value: " + value):
//	}
}
