package fr.pds.isintheair.jdatour.uc.phone.call.receive.entity;

import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.DeviceType;
import fr.pds.isintheair.jdatour.uc.phone.call.receive.enumeration.MessageType;

public class MessageMeta {
    private DeviceType deviceType;
    private String errorDescription;
    private MessageType messageType;
    private Integer statusCode;

    public MessageMeta(MessageMetaBuilder messageMetaBuilder) {
        this.deviceType = messageMetaBuilder.deviceType;
        this.errorDescription = messageMetaBuilder.errorDescription;
        this.messageType = messageMetaBuilder.messageType;
        this.statusCode = messageMetaBuilder.statusCode;
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

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public static class MessageMetaBuilder {
        private DeviceType deviceType;
        private String errorDescription;
        private MessageType messageType;
        private Integer statusCode;

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
