// File: BackupResult.java
//
// Copyright (c) 2022-2023 Liqid, Inc. All rights reserved.
// 
// Redistribution and use in source and binary forms, with or without
// modification, are not permitted without prior consent.
//
// Liqid SDK - Version 3.4
// This file was automatically generated - do not modify it directly.
//

package com.liqid.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;

/**
 * BackupResult
 * Backups up the Director configuration
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BackupResult {

    public static class BackupResultWrapper extends Wrapper<BackupResult>{}

    /**
     * The command line used to perform the backup operation
     */
    @JsonProperty("commandLine")
    private String _commandLine = null;

    public String getCommandLine() {
        return _commandLine;
    }

    public void setCommandLine(String value) {
        _commandLine = value;
    }

    /**
     * Resulting value of the backup operation; zero indicates success
     */
    @JsonProperty("exitValue")
    private Integer _exitValue = null;

    public Integer getExitValue() {
        return _exitValue;
    }

    public void setExitValue(Integer value) {
        _exitValue = value;
    }

    /**
     * Content written to stderr during the backup operation
     */
    @JsonProperty("standardError")
    private String _standardError = null;

    public String getStandardError() {
        return _standardError;
    }

    public void setStandardError(String value) {
        _standardError = value;
    }

    /**
     * Content written to stdout during the backup operation
     */
    @JsonProperty("standardOutput")
    private String _standardOutput = null;

    public String getStandardOutput() {
        return _standardOutput;
    }

    public void setStandardOutput(String value) {
        _standardOutput = value;
    }

    /**
     * Default Constructor
     * Any contained objects are also default-constructed
     */
    public BackupResult() {
    }

    /**
     * Parameterized Constructor
     */
    protected BackupResult(String commandLine,
                           String standardOutput,
                           String standardError,
                           Integer exitValue) {
        _commandLine = commandLine;
        _standardOutput = standardOutput;
        _standardError = standardError;
        _exitValue = exitValue;
    }

    /**
     * String-izer
     */
    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("{");
        sb.append("_commandLine:").append(getCommandLine());
        sb.append(", ").append("_standardOutput:").append(getStandardOutput());
        sb.append(", ").append("_standardError:").append(getStandardError());
        sb.append(", ").append("_exitValue:").append(getExitValue());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Builder class
     */
    public static class Builder {

        private String _commandLine = null;
        private String _standardOutput = null;
        private String _standardError = null;
        private Integer _exitValue = null;

        public Builder setCommandLine(String value) { _commandLine = value; return this; }
        public Builder setStandardOutput(String value) { _standardOutput = value; return this; }
        public Builder setStandardError(String value) { _standardError = value; return this; }
        public Builder setExitValue(Integer value) { _exitValue = value; return this; }

        public BackupResult build() {
            if (_commandLine == null) {
                throw new RuntimeException("setCommandLine() was not invoked in Builder for class BackupResult");
            }
            if (_standardOutput == null) {
                throw new RuntimeException("setStandardOutput() was not invoked in Builder for class BackupResult");
            }
            if (_standardError == null) {
                throw new RuntimeException("setStandardError() was not invoked in Builder for class BackupResult");
            }
            if (_exitValue == null) {
                throw new RuntimeException("setExitValue() was not invoked in Builder for class BackupResult");
            }
            return new BackupResult(_commandLine,
                                    _standardOutput,
                                    _standardError,
                                    _exitValue);
        }
    }
}
