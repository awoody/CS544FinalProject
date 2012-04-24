package org.hsqldb.analysis;

/**
 * These are the broad categories that all SQL commands are being 
 * forced into for the purposes of this project.  However, adding
 * a new one in here should be a simple matter of adding it to this
 * enum and handling it with the StatementTypeClassifier.  The rest
 * of the code should handle the addition automatically.
 * 
 * @author DeepBlue
 *
 */
public enum SQLCommand 
{
	INSERT, UPDATE, DELETE, SELECT, ALTER, UNKNOWN;
}
