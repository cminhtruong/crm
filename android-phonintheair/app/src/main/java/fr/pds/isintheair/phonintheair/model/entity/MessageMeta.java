package fr.pds.isintheair.phonintheair.model.entity;

import fr.pds.isintheair.phonintheair.model.enumeration.DeviceType;
import fr.pds.isintheair.phonintheair.model.enumeration.MessageType;

/******************************************
 * Created by        : jdatour            *
 * Creation date     : 01/24/16           *
 * Modified by       :                    *
 * Modification date :                    *
 ******************************************/

public class MessageMeta {
    private DeviceType  deviceType;
    private String      errorDescription;
    private MessageType messageType;
    private Integer     statusCode;

    public MessageMeta(MessageMetaBuilder messageMetaBuilder) {
        this.deviceType = messageMetaBuilder.deviceType;
        this.messageType = messageMetaBuilder.messageType;
        this.statusCode = messageMetaBuilder.statusCode;
        this.errorDescription = messageMetaBuilder.errorDescription;
    }

    public static MessageMeta buildStandardMessageMeta(MessageType messageType) {
        return new MessageMetaBuilder().addMessageType(messageType).build();
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public static class MessageMetaBuilder {
        private DeviceType  deviceType;
        private String      errorDescription;
        private MessageType messageType;
        private Integer     statusCode;

        public MessageMetaBuilder addDeviceType(DeviceType deviceType) {
            this.deviceType = deviceType;

            return this;
        }

        public MessageMetaBuilder addErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;

            return this;
        }

        public MessageMetaBuilder addMessageType(MessageType messageType) {
            this.messageType = messageType;

            return this;
        }

        public MessageMetaBuilder addStatusCode(Integer statusCode) {
            this.statusCode = statusCode;

            return this;
        }

        public MessageMeta build() {
            return new MessageMeta(this);
        }
    }
}