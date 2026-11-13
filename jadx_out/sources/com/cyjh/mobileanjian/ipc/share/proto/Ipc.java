package com.cyjh.mobileanjian.ipc.share.proto;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.UnmodifiableLazyStringList;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Ipc {

    /* JADX INFO: renamed from: a */
    private static Descriptors.Descriptor f1046a;

    /* JADX INFO: renamed from: b */
    private static GeneratedMessage.FieldAccessorTable f1047b;

    /* JADX INFO: renamed from: c */
    private static Descriptors.Descriptor f1048c;

    /* JADX INFO: renamed from: d */
    private static GeneratedMessage.FieldAccessorTable f1049d;

    /* JADX INFO: renamed from: e */
    private static Descriptors.Descriptor f1050e;

    /* JADX INFO: renamed from: f */
    private static GeneratedMessage.FieldAccessorTable f1051f;

    /* JADX INFO: renamed from: g */
    private static Descriptors.FileDescriptor f1052g;

    public interface DeviceOrBuilder extends MessageOrBuilder {
        String getImei();

        ByteString getImeiBytes();

        String getMac();

        ByteString getMacBytes();

        boolean hasImei();

        boolean hasMac();
    }

    public interface IpcMessageOrBuilder extends MessageOrBuilder {
        int getArg1(int i);

        int getArg1Count();

        List<Integer> getArg1List();

        String getArg2(int i);

        ByteString getArg2Bytes(int i);

        int getArg2Count();

        List<String> getArg2List();

        float getArg3(int i);

        int getArg3Count();

        List<Float> getArg3List();

        long getArg4(int i);

        int getArg4Count();

        List<Long> getArg4List();

        String getClassName();

        ByteString getClassNameBytes();

        int getCmd();

        boolean getEncrypt();

        ByteString getFileData();

        boolean getIsDebug();

        boolean getIsSyncCall();

        String getMethodName();

        ByteString getMethodNameBytes();

        String getParams(int i);

        ByteString getParamsBytes(int i);

        int getParamsCount();

        List<String> getParamsList();

        String getPkgName();

        ByteString getPkgNameBytes();

        ReturnValue getRetValue();

        ReturnValueOrBuilder getRetValueOrBuilder();

        String getTypes(int i);

        ByteString getTypesBytes(int i);

        int getTypesCount();

        List<String> getTypesList();

        int getWaitId();

        boolean hasClassName();

        boolean hasCmd();

        boolean hasEncrypt();

        boolean hasFileData();

        boolean hasIsDebug();

        boolean hasIsSyncCall();

        boolean hasMethodName();

        boolean hasPkgName();

        boolean hasRetValue();

        boolean hasWaitId();
    }

    public interface ReturnValueOrBuilder extends MessageOrBuilder {
        FundType getType();

        boolean getValBoolean();

        double getValDouble();

        float getValFloat();

        int getValInt();

        long getValLong();

        String getValString();

        ByteString getValStringBytes();

        boolean hasType();

        boolean hasValBoolean();

        boolean hasValDouble();

        boolean hasValFloat();

        boolean hasValInt();

        boolean hasValLong();

        boolean hasValString();
    }

    /* JADX INFO: renamed from: h */
    private static void m767h() {
    }

    private Ipc() {
    }

    public enum FundType implements ProtocolMessageEnum {
        VOID(0, 1),
        BOOLEAN(1, 2),
        INT(2, 3),
        LONG(3, 4),
        FLOAT(4, 5),
        DOUBLE(5, 6),
        STRING(6, 7);

        public static final int BOOLEAN_VALUE = 2;
        public static final int DOUBLE_VALUE = 6;
        public static final int FLOAT_VALUE = 5;
        public static final int INT_VALUE = 3;
        public static final int LONG_VALUE = 4;
        public static final int STRING_VALUE = 7;
        public static final int VOID_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<FundType> internalValueMap = new Internal.EnumLiteMap<FundType>() { // from class: com.cyjh.mobileanjian.ipc.share.proto.Ipc.FundType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public final FundType findValueByNumber(int i) {
                return FundType.valueOf(i);
            }
        };
        private static final FundType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static FundType valueOf(int i) {
            switch (i) {
                case 1:
                    return VOID;
                case 2:
                    return BOOLEAN;
                case 3:
                    return INT;
                case 4:
                    return LONG;
                case 5:
                    return FLOAT;
                case 6:
                    return DOUBLE;
                case 7:
                    return STRING;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<FundType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Ipc.m754a().getEnumTypes().get(0);
        }

        public static FundType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        FundType(int i, int i2) {
            this.index = i;
            this.value = i2;
        }
    }

    public static final class Device extends GeneratedMessage implements DeviceOrBuilder {
        public static final int IMEI_FIELD_NUMBER = 1;
        public static final int MAC_FIELD_NUMBER = 2;
        public static Parser<Device> PARSER = new AbstractParser<Device>() { // from class: com.cyjh.mobileanjian.ipc.share.proto.Ipc.Device.1
            @Override // com.google.protobuf.Parser
            public final Device parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Device(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Device defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private Object imei_;
        private Object mac_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private Device(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Device(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Device getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public final Device getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Device(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 10) {
                                    this.bitField0_ |= 1;
                                    this.imei_ = codedInputStream.readBytes();
                                } else if (tag != 18) {
                                    if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 2;
                                    this.mac_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = builderNewBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Ipc.f1046a;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected final GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Ipc.f1047b.ensureFieldAccessorsInitialized(Device.class, Builder.class);
        }

        static {
            Device device = new Device(true);
            defaultInstance = device;
            device.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Parser<Device> getParserForType() {
            return PARSER;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
        public final boolean hasImei() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
        public final String getImei() {
            Object obj = this.imei_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.imei_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
        public final ByteString getImeiBytes() {
            Object obj = this.imei_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.imei_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
        public final boolean hasMac() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
        public final String getMac() {
            Object obj = this.mac_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.mac_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
        public final ByteString getMacBytes() {
            Object obj = this.mac_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.mac_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        private void initFields() {
            this.imei_ = "";
            this.mac_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b != -1) {
                return b == 1;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getImeiBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, getMacBytes());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int iComputeBytesSize = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeBytesSize(1, getImeiBytes()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                iComputeBytesSize += CodedOutputStream.computeBytesSize(2, getMacBytes());
            }
            int serializedSize = iComputeBytesSize + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected final Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Device parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Device parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Device parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Device parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Device parseFrom(InputStream inputStream) throws IOException {
            return PARSER.parseFrom(inputStream);
        }

        public static Device parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Device parseDelimitedFrom(InputStream inputStream) throws IOException {
            return PARSER.parseDelimitedFrom(inputStream);
        }

        public static Device parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Device parseFrom(CodedInputStream codedInputStream) throws IOException {
            return PARSER.parseFrom(codedInputStream);
        }

        public static Device parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Device device) {
            return newBuilder().mergeFrom(device);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public final Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements DeviceOrBuilder {
            private int bitField0_;
            private Object imei_;
            private Object mac_;

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Ipc.f1046a;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected final GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Ipc.f1047b.ensureFieldAccessorsInitialized(Device.class, Builder.class);
            }

            private Builder() {
                this.imei_ = "";
                this.mac_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.imei_ = "";
                this.mac_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Device.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Builder clear() {
                super.clear();
                this.imei_ = "";
                this.bitField0_ &= -2;
                this.mac_ = "";
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
            public final Builder mo5180clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public final Descriptors.Descriptor getDescriptorForType() {
                return Ipc.f1046a;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public final Device getDefaultInstanceForType() {
                return Device.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Device build() {
                Device deviceBuildPartial = buildPartial();
                if (deviceBuildPartial.isInitialized()) {
                    return deviceBuildPartial;
                }
                throw newUninitializedMessageException((Message) deviceBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Device buildPartial() {
                Device device = new Device(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                device.imei_ = this.imei_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                device.mac_ = this.mac_;
                device.bitField0_ = i2;
                onBuilt();
                return device;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeFrom(Message message) {
                if (message instanceof Device) {
                    return mergeFrom((Device) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public final Builder mergeFrom(Device device) {
                if (device == Device.getDefaultInstance()) {
                    return this;
                }
                if (device.hasImei()) {
                    this.bitField0_ |= 1;
                    this.imei_ = device.imei_;
                    onChanged();
                }
                if (device.hasMac()) {
                    this.bitField0_ |= 2;
                    this.mac_ = device.mac_;
                    onChanged();
                }
                mergeUnknownFields(device.getUnknownFields());
                return this;
            }

            /* JADX WARN: Code duplicated, block: B:15:0x001d  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
                Device device = null;
                try {
                    try {
                        Device partialFrom = Device.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (partialFrom != null) {
                            mergeFrom(partialFrom);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        Device device2 = (Device) e.getUnfinishedMessage();
                        try {
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            device = device2;
                            if (device != null) {
                                mergeFrom(device);
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (device != null) {
                        mergeFrom(device);
                    }
                    throw th;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
            public final boolean hasImei() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
            public final String getImei() {
                Object obj = this.imei_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.imei_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
            public final ByteString getImeiBytes() {
                Object obj = this.imei_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.imei_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public final Builder setImei(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.imei_ = str;
                onChanged();
                return this;
            }

            public final Builder clearImei() {
                this.bitField0_ &= -2;
                this.imei_ = Device.getDefaultInstance().getImei();
                onChanged();
                return this;
            }

            public final Builder setImeiBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.imei_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
            public final boolean hasMac() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
            public final String getMac() {
                Object obj = this.mac_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.mac_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.DeviceOrBuilder
            public final ByteString getMacBytes() {
                Object obj = this.mac_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.mac_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public final Builder setMac(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.mac_ = str;
                onChanged();
                return this;
            }

            public final Builder clearMac() {
                this.bitField0_ &= -3;
                this.mac_ = Device.getDefaultInstance().getMac();
                onChanged();
                return this;
            }

            public final Builder setMacBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.mac_ = byteString;
                onChanged();
                return this;
            }
        }
    }

    public static final class ReturnValue extends GeneratedMessage implements ReturnValueOrBuilder {
        public static Parser<ReturnValue> PARSER = new AbstractParser<ReturnValue>() { // from class: com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValue.1
            @Override // com.google.protobuf.Parser
            public final ReturnValue parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ReturnValue(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int VALBOOLEAN_FIELD_NUMBER = 2;
        public static final int VALDOUBLE_FIELD_NUMBER = 6;
        public static final int VALFLOAT_FIELD_NUMBER = 5;
        public static final int VALINT_FIELD_NUMBER = 3;
        public static final int VALLONG_FIELD_NUMBER = 4;
        public static final int VALSTRING_FIELD_NUMBER = 7;
        private static final ReturnValue defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private FundType type_;
        private final UnknownFieldSet unknownFields;
        private boolean valBoolean_;
        private double valDouble_;
        private float valFloat_;
        private int valInt_;
        private long valLong_;
        private Object valString_;

        private ReturnValue(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ReturnValue(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ReturnValue getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public final ReturnValue getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ReturnValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag == 8) {
                                    int i = codedInputStream.readEnum();
                                    FundType fundTypeValueOf = FundType.valueOf(i);
                                    if (fundTypeValueOf == null) {
                                        builderNewBuilder.mergeVarintField(1, i);
                                    } else {
                                        this.bitField0_ |= 1;
                                        this.type_ = fundTypeValueOf;
                                    }
                                } else if (tag == 16) {
                                    this.bitField0_ |= 2;
                                    this.valBoolean_ = codedInputStream.readBool();
                                } else if (tag == 24) {
                                    this.bitField0_ |= 4;
                                    this.valInt_ = codedInputStream.readInt32();
                                } else if (tag == 32) {
                                    this.bitField0_ |= 8;
                                    this.valLong_ = codedInputStream.readInt64();
                                } else if (tag == 45) {
                                    this.bitField0_ |= 16;
                                    this.valFloat_ = codedInputStream.readFloat();
                                } else if (tag == 49) {
                                    this.bitField0_ |= 32;
                                    this.valDouble_ = codedInputStream.readDouble();
                                } else if (tag != 58) {
                                    if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    this.bitField0_ |= 64;
                                    this.valString_ = codedInputStream.readBytes();
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = builderNewBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Ipc.f1048c;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected final GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Ipc.f1049d.ensureFieldAccessorsInitialized(ReturnValue.class, Builder.class);
        }

        static {
            ReturnValue returnValue = new ReturnValue(true);
            defaultInstance = returnValue;
            returnValue.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Parser<ReturnValue> getParserForType() {
            return PARSER;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final FundType getType() {
            return this.type_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean hasValBoolean() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean getValBoolean() {
            return this.valBoolean_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean hasValInt() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final int getValInt() {
            return this.valInt_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean hasValLong() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final long getValLong() {
            return this.valLong_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean hasValFloat() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final float getValFloat() {
            return this.valFloat_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean hasValDouble() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final double getValDouble() {
            return this.valDouble_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final boolean hasValString() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final String getValString() {
            Object obj = this.valString_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.valString_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
        public final ByteString getValStringBytes() {
            Object obj = this.valString_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.valString_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        private void initFields() {
            this.type_ = FundType.VOID;
            this.valBoolean_ = false;
            this.valInt_ = 0;
            this.valLong_ = 0L;
            this.valFloat_ = 0.0f;
            this.valDouble_ = 0.0d;
            this.valString_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b != -1) {
                return b == 1;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.type_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBool(2, this.valBoolean_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.valInt_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt64(4, this.valLong_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeFloat(5, this.valFloat_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeDouble(6, this.valDouble_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeBytes(7, getValStringBytes());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int iComputeEnumSize = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.type_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                iComputeEnumSize += CodedOutputStream.computeBoolSize(2, this.valBoolean_);
            }
            if ((this.bitField0_ & 4) == 4) {
                iComputeEnumSize += CodedOutputStream.computeInt32Size(3, this.valInt_);
            }
            if ((this.bitField0_ & 8) == 8) {
                iComputeEnumSize += CodedOutputStream.computeInt64Size(4, this.valLong_);
            }
            if ((this.bitField0_ & 16) == 16) {
                iComputeEnumSize += CodedOutputStream.computeFloatSize(5, this.valFloat_);
            }
            if ((this.bitField0_ & 32) == 32) {
                iComputeEnumSize += CodedOutputStream.computeDoubleSize(6, this.valDouble_);
            }
            if ((this.bitField0_ & 64) == 64) {
                iComputeEnumSize += CodedOutputStream.computeBytesSize(7, getValStringBytes());
            }
            int serializedSize = iComputeEnumSize + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected final Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ReturnValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ReturnValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ReturnValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ReturnValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ReturnValue parseFrom(InputStream inputStream) throws IOException {
            return PARSER.parseFrom(inputStream);
        }

        public static ReturnValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ReturnValue parseDelimitedFrom(InputStream inputStream) throws IOException {
            return PARSER.parseDelimitedFrom(inputStream);
        }

        public static ReturnValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ReturnValue parseFrom(CodedInputStream codedInputStream) throws IOException {
            return PARSER.parseFrom(codedInputStream);
        }

        public static ReturnValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ReturnValue returnValue) {
            return newBuilder().mergeFrom(returnValue);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public final Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ReturnValueOrBuilder {
            private int bitField0_;
            private FundType type_;
            private boolean valBoolean_;
            private double valDouble_;
            private float valFloat_;
            private int valInt_;
            private long valLong_;
            private Object valString_;

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Ipc.f1048c;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected final GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Ipc.f1049d.ensureFieldAccessorsInitialized(ReturnValue.class, Builder.class);
            }

            private Builder() {
                this.type_ = FundType.VOID;
                this.valString_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.type_ = FundType.VOID;
                this.valString_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ReturnValue.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Builder clear() {
                super.clear();
                this.type_ = FundType.VOID;
                this.bitField0_ &= -2;
                this.valBoolean_ = false;
                this.bitField0_ &= -3;
                this.valInt_ = 0;
                this.bitField0_ &= -5;
                this.valLong_ = 0L;
                this.bitField0_ &= -9;
                this.valFloat_ = 0.0f;
                this.bitField0_ &= -17;
                this.valDouble_ = 0.0d;
                this.bitField0_ &= -33;
                this.valString_ = "";
                this.bitField0_ &= -65;
                return this;
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public final Builder mo5180clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public final Descriptors.Descriptor getDescriptorForType() {
                return Ipc.f1048c;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public final ReturnValue getDefaultInstanceForType() {
                return ReturnValue.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final ReturnValue build() {
                ReturnValue returnValueBuildPartial = buildPartial();
                if (returnValueBuildPartial.isInitialized()) {
                    return returnValueBuildPartial;
                }
                throw newUninitializedMessageException((Message) returnValueBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final ReturnValue buildPartial() {
                ReturnValue returnValue = new ReturnValue(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                returnValue.type_ = this.type_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                returnValue.valBoolean_ = this.valBoolean_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                returnValue.valInt_ = this.valInt_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                returnValue.valLong_ = this.valLong_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                returnValue.valFloat_ = this.valFloat_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                returnValue.valDouble_ = this.valDouble_;
                if ((i & 64) == 64) {
                    i2 |= 64;
                }
                returnValue.valString_ = this.valString_;
                returnValue.bitField0_ = i2;
                onBuilt();
                return returnValue;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeFrom(Message message) {
                if (message instanceof ReturnValue) {
                    return mergeFrom((ReturnValue) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public final Builder mergeFrom(ReturnValue returnValue) {
                if (returnValue == ReturnValue.getDefaultInstance()) {
                    return this;
                }
                if (returnValue.hasType()) {
                    setType(returnValue.getType());
                }
                if (returnValue.hasValBoolean()) {
                    setValBoolean(returnValue.getValBoolean());
                }
                if (returnValue.hasValInt()) {
                    setValInt(returnValue.getValInt());
                }
                if (returnValue.hasValLong()) {
                    setValLong(returnValue.getValLong());
                }
                if (returnValue.hasValFloat()) {
                    setValFloat(returnValue.getValFloat());
                }
                if (returnValue.hasValDouble()) {
                    setValDouble(returnValue.getValDouble());
                }
                if (returnValue.hasValString()) {
                    this.bitField0_ |= 64;
                    this.valString_ = returnValue.valString_;
                    onChanged();
                }
                mergeUnknownFields(returnValue.getUnknownFields());
                return this;
            }

            /* JADX WARN: Code duplicated, block: B:15:0x001d  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
                ReturnValue returnValue = null;
                try {
                    try {
                        ReturnValue partialFrom = ReturnValue.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (partialFrom != null) {
                            mergeFrom(partialFrom);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        ReturnValue returnValue2 = (ReturnValue) e.getUnfinishedMessage();
                        try {
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            returnValue = returnValue2;
                            if (returnValue != null) {
                                mergeFrom(returnValue);
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (returnValue != null) {
                        mergeFrom(returnValue);
                    }
                    throw th;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean hasType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final FundType getType() {
                return this.type_;
            }

            public final Builder setType(FundType fundType) {
                if (fundType == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.type_ = fundType;
                onChanged();
                return this;
            }

            public final Builder clearType() {
                this.bitField0_ &= -2;
                this.type_ = FundType.VOID;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean hasValBoolean() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean getValBoolean() {
                return this.valBoolean_;
            }

            public final Builder setValBoolean(boolean z) {
                this.bitField0_ |= 2;
                this.valBoolean_ = z;
                onChanged();
                return this;
            }

            public final Builder clearValBoolean() {
                this.bitField0_ &= -3;
                this.valBoolean_ = false;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean hasValInt() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final int getValInt() {
                return this.valInt_;
            }

            public final Builder setValInt(int i) {
                this.bitField0_ |= 4;
                this.valInt_ = i;
                onChanged();
                return this;
            }

            public final Builder clearValInt() {
                this.bitField0_ &= -5;
                this.valInt_ = 0;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean hasValLong() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final long getValLong() {
                return this.valLong_;
            }

            public final Builder setValLong(long j) {
                this.bitField0_ |= 8;
                this.valLong_ = j;
                onChanged();
                return this;
            }

            public final Builder clearValLong() {
                this.bitField0_ &= -9;
                this.valLong_ = 0L;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean hasValFloat() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final float getValFloat() {
                return this.valFloat_;
            }

            public final Builder setValFloat(float f) {
                this.bitField0_ |= 16;
                this.valFloat_ = f;
                onChanged();
                return this;
            }

            public final Builder clearValFloat() {
                this.bitField0_ &= -17;
                this.valFloat_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean hasValDouble() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final double getValDouble() {
                return this.valDouble_;
            }

            public final Builder setValDouble(double d) {
                this.bitField0_ |= 32;
                this.valDouble_ = d;
                onChanged();
                return this;
            }

            public final Builder clearValDouble() {
                this.bitField0_ &= -33;
                this.valDouble_ = 0.0d;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final boolean hasValString() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final String getValString() {
                Object obj = this.valString_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.valString_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.ReturnValueOrBuilder
            public final ByteString getValStringBytes() {
                Object obj = this.valString_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.valString_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public final Builder setValString(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.valString_ = str;
                onChanged();
                return this;
            }

            public final Builder clearValString() {
                this.bitField0_ &= -65;
                this.valString_ = ReturnValue.getDefaultInstance().getValString();
                onChanged();
                return this;
            }

            public final Builder setValStringBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.valString_ = byteString;
                onChanged();
                return this;
            }
        }
    }

    public static final class IpcMessage extends GeneratedMessage implements IpcMessageOrBuilder {
        public static final int ARG1_FIELD_NUMBER = 2;
        public static final int ARG2_FIELD_NUMBER = 3;
        public static final int ARG3_FIELD_NUMBER = 4;
        public static final int ARG4_FIELD_NUMBER = 6;
        public static final int CLASSNAME_FIELD_NUMBER = 8;
        public static final int CMD_FIELD_NUMBER = 1;
        public static final int ENCRYPT_FIELD_NUMBER = 15;
        public static final int FILEDATA_FIELD_NUMBER = 5;
        public static final int ISDEBUG_FIELD_NUMBER = 16;
        public static final int ISSYNCCALL_FIELD_NUMBER = 12;
        public static final int METHODNAME_FIELD_NUMBER = 9;
        public static final int PARAMS_FIELD_NUMBER = 11;
        public static Parser<IpcMessage> PARSER = new AbstractParser<IpcMessage>() { // from class: com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessage.1
            @Override // com.google.protobuf.Parser
            public final IpcMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new IpcMessage(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PKGNAME_FIELD_NUMBER = 7;
        public static final int RETVALUE_FIELD_NUMBER = 14;
        public static final int TYPES_FIELD_NUMBER = 10;
        public static final int WAITID_FIELD_NUMBER = 13;
        private static final IpcMessage defaultInstance;
        private static final long serialVersionUID = 0;
        private List<Integer> arg1_;
        private LazyStringList arg2_;
        private List<Float> arg3_;
        private List<Long> arg4_;
        private int bitField0_;
        private Object className_;
        private int cmd_;
        private boolean encrypt_;
        private ByteString fileData_;
        private boolean isDebug_;
        private boolean isSyncCall_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object methodName_;
        private LazyStringList params_;
        private Object pkgName_;
        private ReturnValue retValue_;
        private LazyStringList types_;
        private final UnknownFieldSet unknownFields;
        private int waitId_;

        private IpcMessage(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private IpcMessage(boolean z) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static IpcMessage getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public final IpcMessage getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private IpcMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        switch (tag) {
                            case 0:
                                z = true;
                                break;
                            case 8:
                                this.bitField0_ |= 1;
                                this.cmd_ = codedInputStream.readInt32();
                                break;
                            case 16:
                                if ((i & 2) != 2) {
                                    this.arg1_ = new ArrayList();
                                    i |= 2;
                                }
                                this.arg1_.add(Integer.valueOf(codedInputStream.readInt32()));
                                break;
                            case 18:
                                int iPushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 2) != 2 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.arg1_ = new ArrayList();
                                    i |= 2;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.arg1_.add(Integer.valueOf(codedInputStream.readInt32()));
                                }
                                codedInputStream.popLimit(iPushLimit);
                                break;
                            case 26:
                                if ((i & 4) != 4) {
                                    this.arg2_ = new LazyStringArrayList();
                                    i |= 4;
                                }
                                this.arg2_.add(codedInputStream.readBytes());
                                break;
                            case 34:
                                int iPushLimit2 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 8) != 8 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.arg3_ = new ArrayList();
                                    i |= 8;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.arg3_.add(Float.valueOf(codedInputStream.readFloat()));
                                }
                                codedInputStream.popLimit(iPushLimit2);
                                break;
                            case 37:
                                if ((i & 8) != 8) {
                                    this.arg3_ = new ArrayList();
                                    i |= 8;
                                }
                                this.arg3_.add(Float.valueOf(codedInputStream.readFloat()));
                                break;
                            case 42:
                                this.bitField0_ |= 2;
                                this.fileData_ = codedInputStream.readBytes();
                                break;
                            case 48:
                                if ((i & 32) != 32) {
                                    this.arg4_ = new ArrayList();
                                    i |= 32;
                                }
                                this.arg4_.add(Long.valueOf(codedInputStream.readInt64()));
                                break;
                            case 50:
                                int iPushLimit3 = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                if ((i & 32) != 32 && codedInputStream.getBytesUntilLimit() > 0) {
                                    this.arg4_ = new ArrayList();
                                    i |= 32;
                                }
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.arg4_.add(Long.valueOf(codedInputStream.readInt64()));
                                }
                                codedInputStream.popLimit(iPushLimit3);
                                break;
                            case 58:
                                this.bitField0_ |= 4;
                                this.pkgName_ = codedInputStream.readBytes();
                                break;
                            case 66:
                                this.bitField0_ |= 8;
                                this.className_ = codedInputStream.readBytes();
                                break;
                            case 74:
                                this.bitField0_ |= 16;
                                this.methodName_ = codedInputStream.readBytes();
                                break;
                            case 82:
                                if ((i & 512) != 512) {
                                    this.types_ = new LazyStringArrayList();
                                    i |= 512;
                                }
                                this.types_.add(codedInputStream.readBytes());
                                break;
                            case 90:
                                if ((i & 1024) != 1024) {
                                    this.params_ = new LazyStringArrayList();
                                    i |= 1024;
                                }
                                this.params_.add(codedInputStream.readBytes());
                                break;
                            case 96:
                                this.bitField0_ |= 32;
                                this.isSyncCall_ = codedInputStream.readBool();
                                break;
                            case 104:
                                this.bitField0_ |= 64;
                                this.waitId_ = codedInputStream.readInt32();
                                break;
                            case 114:
                                ReturnValue.Builder builder = (this.bitField0_ & 128) == 128 ? this.retValue_.toBuilder() : null;
                                this.retValue_ = (ReturnValue) codedInputStream.readMessage(ReturnValue.PARSER, extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.retValue_);
                                    this.retValue_ = builder.buildPartial();
                                }
                                this.bitField0_ |= 128;
                                break;
                            case 120:
                                this.bitField0_ |= 256;
                                this.encrypt_ = codedInputStream.readBool();
                                break;
                            case 128:
                                this.bitField0_ |= 512;
                                this.isDebug_ = codedInputStream.readBool();
                                break;
                            default:
                                if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    z = true;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } catch (Throwable th) {
                    if ((i & 2) == 2) {
                        this.arg1_ = Collections.unmodifiableList(this.arg1_);
                    }
                    if ((i & 4) == 4) {
                        this.arg2_ = new UnmodifiableLazyStringList(this.arg2_);
                    }
                    if ((i & 8) == 8) {
                        this.arg3_ = Collections.unmodifiableList(this.arg3_);
                    }
                    if ((i & 32) == 32) {
                        this.arg4_ = Collections.unmodifiableList(this.arg4_);
                    }
                    if ((i & 512) == 512) {
                        this.types_ = new UnmodifiableLazyStringList(this.types_);
                    }
                    if ((i & 1024) == 1024) {
                        this.params_ = new UnmodifiableLazyStringList(this.params_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if ((i & 2) == 2) {
                this.arg1_ = Collections.unmodifiableList(this.arg1_);
            }
            if ((i & 4) == 4) {
                this.arg2_ = new UnmodifiableLazyStringList(this.arg2_);
            }
            if ((i & 8) == 8) {
                this.arg3_ = Collections.unmodifiableList(this.arg3_);
            }
            if ((i & 32) == 32) {
                this.arg4_ = Collections.unmodifiableList(this.arg4_);
            }
            if ((i & 512) == 512) {
                this.types_ = new UnmodifiableLazyStringList(this.types_);
            }
            if ((i & 1024) == 1024) {
                this.params_ = new UnmodifiableLazyStringList(this.params_);
            }
            this.unknownFields = builderNewBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return Ipc.f1050e;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected final GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Ipc.f1051f.ensureFieldAccessorsInitialized(IpcMessage.class, Builder.class);
        }

        static {
            IpcMessage ipcMessage = new IpcMessage(true);
            defaultInstance = ipcMessage;
            ipcMessage.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Parser<IpcMessage> getParserForType() {
            return PARSER;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasCmd() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getCmd() {
            return this.cmd_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final List<Integer> getArg1List() {
            return this.arg1_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getArg1Count() {
            return this.arg1_.size();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getArg1(int i) {
            return this.arg1_.get(i).intValue();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final List<String> getArg2List() {
            return this.arg2_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getArg2Count() {
            return this.arg2_.size();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final String getArg2(int i) {
            return this.arg2_.get(i);
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ByteString getArg2Bytes(int i) {
            return this.arg2_.getByteString(i);
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final List<Float> getArg3List() {
            return this.arg3_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getArg3Count() {
            return this.arg3_.size();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final float getArg3(int i) {
            return this.arg3_.get(i).floatValue();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasFileData() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ByteString getFileData() {
            return this.fileData_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final List<Long> getArg4List() {
            return this.arg4_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getArg4Count() {
            return this.arg4_.size();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final long getArg4(int i) {
            return this.arg4_.get(i).longValue();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasPkgName() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final String getPkgName() {
            Object obj = this.pkgName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.pkgName_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ByteString getPkgNameBytes() {
            Object obj = this.pkgName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.pkgName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasClassName() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final String getClassName() {
            Object obj = this.className_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.className_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ByteString getClassNameBytes() {
            Object obj = this.className_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.className_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasMethodName() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final String getMethodName() {
            Object obj = this.methodName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.methodName_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ByteString getMethodNameBytes() {
            Object obj = this.methodName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.methodName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final List<String> getTypesList() {
            return this.types_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getTypesCount() {
            return this.types_.size();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final String getTypes(int i) {
            return this.types_.get(i);
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ByteString getTypesBytes(int i) {
            return this.types_.getByteString(i);
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final List<String> getParamsList() {
            return this.params_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getParamsCount() {
            return this.params_.size();
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final String getParams(int i) {
            return this.params_.get(i);
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ByteString getParamsBytes(int i) {
            return this.params_.getByteString(i);
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasIsSyncCall() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean getIsSyncCall() {
            return this.isSyncCall_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasWaitId() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final int getWaitId() {
            return this.waitId_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasRetValue() {
            return (this.bitField0_ & 128) == 128;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ReturnValue getRetValue() {
            return this.retValue_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final ReturnValueOrBuilder getRetValueOrBuilder() {
            return this.retValue_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasEncrypt() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean getEncrypt() {
            return this.encrypt_;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean hasIsDebug() {
            return (this.bitField0_ & 512) == 512;
        }

        @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
        public final boolean getIsDebug() {
            return this.isDebug_;
        }

        private void initFields() {
            this.cmd_ = 0;
            this.arg1_ = Collections.emptyList();
            this.arg2_ = LazyStringArrayList.EMPTY;
            this.arg3_ = Collections.emptyList();
            this.fileData_ = ByteString.EMPTY;
            this.arg4_ = Collections.emptyList();
            this.pkgName_ = "";
            this.className_ = "";
            this.methodName_ = "";
            this.types_ = LazyStringArrayList.EMPTY;
            this.params_ = LazyStringArrayList.EMPTY;
            this.isSyncCall_ = false;
            this.waitId_ = 0;
            this.retValue_ = ReturnValue.getDefaultInstance();
            this.encrypt_ = false;
            this.isDebug_ = false;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b != -1) {
                return b == 1;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.cmd_);
            }
            for (int i = 0; i < this.arg1_.size(); i++) {
                codedOutputStream.writeInt32(2, this.arg1_.get(i).intValue());
            }
            for (int i2 = 0; i2 < this.arg2_.size(); i2++) {
                codedOutputStream.writeBytes(3, this.arg2_.getByteString(i2));
            }
            for (int i3 = 0; i3 < this.arg3_.size(); i3++) {
                codedOutputStream.writeFloat(4, this.arg3_.get(i3).floatValue());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(5, this.fileData_);
            }
            for (int i4 = 0; i4 < this.arg4_.size(); i4++) {
                codedOutputStream.writeInt64(6, this.arg4_.get(i4).longValue());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(7, getPkgNameBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(8, getClassNameBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(9, getMethodNameBytes());
            }
            for (int i5 = 0; i5 < this.types_.size(); i5++) {
                codedOutputStream.writeBytes(10, this.types_.getByteString(i5));
            }
            for (int i6 = 0; i6 < this.params_.size(); i6++) {
                codedOutputStream.writeBytes(11, this.params_.getByteString(i6));
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBool(12, this.isSyncCall_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeInt32(13, this.waitId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeMessage(14, this.retValue_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeBool(15, this.encrypt_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeBool(16, this.isDebug_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public final int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int iComputeInt32Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt32Size(1, this.cmd_) + 0 : 0;
            int iComputeInt32SizeNoTag = 0;
            for (int i2 = 0; i2 < this.arg1_.size(); i2++) {
                iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(this.arg1_.get(i2).intValue());
            }
            int size = iComputeInt32Size + iComputeInt32SizeNoTag + (getArg1List().size() * 1);
            int iComputeBytesSizeNoTag = 0;
            for (int i3 = 0; i3 < this.arg2_.size(); i3++) {
                iComputeBytesSizeNoTag += CodedOutputStream.computeBytesSizeNoTag(this.arg2_.getByteString(i3));
            }
            int size2 = size + iComputeBytesSizeNoTag + (getArg2List().size() * 1) + (getArg3List().size() * 4) + (getArg3List().size() * 1);
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(5, this.fileData_);
            }
            int iComputeInt64SizeNoTag = 0;
            for (int i4 = 0; i4 < this.arg4_.size(); i4++) {
                iComputeInt64SizeNoTag += CodedOutputStream.computeInt64SizeNoTag(this.arg4_.get(i4).longValue());
            }
            int size3 = size2 + iComputeInt64SizeNoTag + (getArg4List().size() * 1);
            if ((this.bitField0_ & 4) == 4) {
                size3 += CodedOutputStream.computeBytesSize(7, getPkgNameBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size3 += CodedOutputStream.computeBytesSize(8, getClassNameBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                size3 += CodedOutputStream.computeBytesSize(9, getMethodNameBytes());
            }
            int iComputeBytesSizeNoTag2 = 0;
            for (int i5 = 0; i5 < this.types_.size(); i5++) {
                iComputeBytesSizeNoTag2 += CodedOutputStream.computeBytesSizeNoTag(this.types_.getByteString(i5));
            }
            int size4 = size3 + iComputeBytesSizeNoTag2 + (getTypesList().size() * 1);
            int iComputeBytesSizeNoTag3 = 0;
            for (int i6 = 0; i6 < this.params_.size(); i6++) {
                iComputeBytesSizeNoTag3 += CodedOutputStream.computeBytesSizeNoTag(this.params_.getByteString(i6));
            }
            int size5 = size4 + iComputeBytesSizeNoTag3 + (getParamsList().size() * 1);
            if ((this.bitField0_ & 32) == 32) {
                size5 += CodedOutputStream.computeBoolSize(12, this.isSyncCall_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size5 += CodedOutputStream.computeInt32Size(13, this.waitId_);
            }
            if ((this.bitField0_ & 128) == 128) {
                size5 += CodedOutputStream.computeMessageSize(14, this.retValue_);
            }
            if ((this.bitField0_ & 256) == 256) {
                size5 += CodedOutputStream.computeBoolSize(15, this.encrypt_);
            }
            if ((this.bitField0_ & 512) == 512) {
                size5 += CodedOutputStream.computeBoolSize(16, this.isDebug_);
            }
            int serializedSize = size5 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected final Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static IpcMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static IpcMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static IpcMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static IpcMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static IpcMessage parseFrom(InputStream inputStream) throws IOException {
            return PARSER.parseFrom(inputStream);
        }

        public static IpcMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static IpcMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return PARSER.parseDelimitedFrom(inputStream);
        }

        public static IpcMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static IpcMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return PARSER.parseFrom(codedInputStream);
        }

        public static IpcMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(IpcMessage ipcMessage) {
            return newBuilder().mergeFrom(ipcMessage);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public final Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public final Builder newBuilderForType(GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements IpcMessageOrBuilder {
            private List<Integer> arg1_;
            private LazyStringList arg2_;
            private List<Float> arg3_;
            private List<Long> arg4_;
            private int bitField0_;
            private Object className_;
            private int cmd_;
            private boolean encrypt_;
            private ByteString fileData_;
            private boolean isDebug_;
            private boolean isSyncCall_;
            private Object methodName_;
            private LazyStringList params_;
            private Object pkgName_;
            private SingleFieldBuilder<ReturnValue, ReturnValue.Builder, ReturnValueOrBuilder> retValueBuilder_;
            private ReturnValue retValue_;
            private LazyStringList types_;
            private int waitId_;

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return Ipc.f1050e;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected final GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Ipc.f1051f.ensureFieldAccessorsInitialized(IpcMessage.class, Builder.class);
            }

            private Builder() {
                this.arg1_ = Collections.emptyList();
                this.arg2_ = LazyStringArrayList.EMPTY;
                this.arg3_ = Collections.emptyList();
                this.fileData_ = ByteString.EMPTY;
                this.arg4_ = Collections.emptyList();
                this.pkgName_ = "";
                this.className_ = "";
                this.methodName_ = "";
                this.types_ = LazyStringArrayList.EMPTY;
                this.params_ = LazyStringArrayList.EMPTY;
                this.retValue_ = ReturnValue.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.arg1_ = Collections.emptyList();
                this.arg2_ = LazyStringArrayList.EMPTY;
                this.arg3_ = Collections.emptyList();
                this.fileData_ = ByteString.EMPTY;
                this.arg4_ = Collections.emptyList();
                this.pkgName_ = "";
                this.className_ = "";
                this.methodName_ = "";
                this.types_ = LazyStringArrayList.EMPTY;
                this.params_ = LazyStringArrayList.EMPTY;
                this.retValue_ = ReturnValue.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (IpcMessage.alwaysUseFieldBuilders) {
                    getRetValueFieldBuilder();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Builder clear() {
                super.clear();
                this.cmd_ = 0;
                this.bitField0_ &= -2;
                this.arg1_ = Collections.emptyList();
                this.bitField0_ &= -3;
                this.arg2_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -5;
                this.arg3_ = Collections.emptyList();
                this.bitField0_ &= -9;
                this.fileData_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                this.arg4_ = Collections.emptyList();
                this.bitField0_ &= -33;
                this.pkgName_ = "";
                this.bitField0_ &= -65;
                this.className_ = "";
                this.bitField0_ &= -129;
                this.methodName_ = "";
                this.bitField0_ &= -257;
                this.types_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -513;
                this.params_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -1025;
                this.isSyncCall_ = false;
                this.bitField0_ &= -2049;
                this.waitId_ = 0;
                this.bitField0_ &= -4097;
                if (this.retValueBuilder_ == null) {
                    this.retValue_ = ReturnValue.getDefaultInstance();
                } else {
                    this.retValueBuilder_.clear();
                }
                this.bitField0_ &= -8193;
                this.encrypt_ = false;
                this.bitField0_ &= -16385;
                this.isDebug_ = false;
                this.bitField0_ &= -32769;
                return this;
            }

            @Override // com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public final Builder mo5180clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public final Descriptors.Descriptor getDescriptorForType() {
                return Ipc.f1050e;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public final IpcMessage getDefaultInstanceForType() {
                return IpcMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final IpcMessage build() {
                IpcMessage ipcMessageBuildPartial = buildPartial();
                if (ipcMessageBuildPartial.isInitialized()) {
                    return ipcMessageBuildPartial;
                }
                throw newUninitializedMessageException((Message) ipcMessageBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final IpcMessage buildPartial() {
                IpcMessage ipcMessage = new IpcMessage(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                ipcMessage.cmd_ = this.cmd_;
                if ((this.bitField0_ & 2) == 2) {
                    this.arg1_ = Collections.unmodifiableList(this.arg1_);
                    this.bitField0_ &= -3;
                }
                ipcMessage.arg1_ = this.arg1_;
                if ((this.bitField0_ & 4) == 4) {
                    this.arg2_ = new UnmodifiableLazyStringList(this.arg2_);
                    this.bitField0_ &= -5;
                }
                ipcMessage.arg2_ = this.arg2_;
                if ((this.bitField0_ & 8) == 8) {
                    this.arg3_ = Collections.unmodifiableList(this.arg3_);
                    this.bitField0_ &= -9;
                }
                ipcMessage.arg3_ = this.arg3_;
                if ((i & 16) == 16) {
                    i2 |= 2;
                }
                ipcMessage.fileData_ = this.fileData_;
                if ((this.bitField0_ & 32) == 32) {
                    this.arg4_ = Collections.unmodifiableList(this.arg4_);
                    this.bitField0_ &= -33;
                }
                ipcMessage.arg4_ = this.arg4_;
                if ((i & 64) == 64) {
                    i2 |= 4;
                }
                ipcMessage.pkgName_ = this.pkgName_;
                if ((i & 128) == 128) {
                    i2 |= 8;
                }
                ipcMessage.className_ = this.className_;
                if ((i & 256) == 256) {
                    i2 |= 16;
                }
                ipcMessage.methodName_ = this.methodName_;
                if ((this.bitField0_ & 512) == 512) {
                    this.types_ = new UnmodifiableLazyStringList(this.types_);
                    this.bitField0_ &= -513;
                }
                ipcMessage.types_ = this.types_;
                if ((this.bitField0_ & 1024) == 1024) {
                    this.params_ = new UnmodifiableLazyStringList(this.params_);
                    this.bitField0_ &= -1025;
                }
                ipcMessage.params_ = this.params_;
                if ((i & 2048) == 2048) {
                    i2 |= 32;
                }
                ipcMessage.isSyncCall_ = this.isSyncCall_;
                if ((i & 4096) == 4096) {
                    i2 |= 64;
                }
                ipcMessage.waitId_ = this.waitId_;
                if ((i & 8192) == 8192) {
                    i2 |= 128;
                }
                if (this.retValueBuilder_ == null) {
                    ipcMessage.retValue_ = this.retValue_;
                } else {
                    ipcMessage.retValue_ = (ReturnValue) this.retValueBuilder_.build();
                }
                if ((i & 16384) == 16384) {
                    i2 |= 256;
                }
                ipcMessage.encrypt_ = this.encrypt_;
                if ((i & 32768) == 32768) {
                    i2 |= 512;
                }
                ipcMessage.isDebug_ = this.isDebug_;
                ipcMessage.bitField0_ = i2;
                onBuilt();
                return ipcMessage;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeFrom(Message message) {
                if (message instanceof IpcMessage) {
                    return mergeFrom((IpcMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public final Builder mergeFrom(IpcMessage ipcMessage) {
                if (ipcMessage == IpcMessage.getDefaultInstance()) {
                    return this;
                }
                if (ipcMessage.hasCmd()) {
                    setCmd(ipcMessage.getCmd());
                }
                if (!ipcMessage.arg1_.isEmpty()) {
                    if (this.arg1_.isEmpty()) {
                        this.arg1_ = ipcMessage.arg1_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureArg1IsMutable();
                        this.arg1_.addAll(ipcMessage.arg1_);
                    }
                    onChanged();
                }
                if (!ipcMessage.arg2_.isEmpty()) {
                    if (this.arg2_.isEmpty()) {
                        this.arg2_ = ipcMessage.arg2_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureArg2IsMutable();
                        this.arg2_.addAll(ipcMessage.arg2_);
                    }
                    onChanged();
                }
                if (!ipcMessage.arg3_.isEmpty()) {
                    if (this.arg3_.isEmpty()) {
                        this.arg3_ = ipcMessage.arg3_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureArg3IsMutable();
                        this.arg3_.addAll(ipcMessage.arg3_);
                    }
                    onChanged();
                }
                if (ipcMessage.hasFileData()) {
                    setFileData(ipcMessage.getFileData());
                }
                if (!ipcMessage.arg4_.isEmpty()) {
                    if (this.arg4_.isEmpty()) {
                        this.arg4_ = ipcMessage.arg4_;
                        this.bitField0_ &= -33;
                    } else {
                        ensureArg4IsMutable();
                        this.arg4_.addAll(ipcMessage.arg4_);
                    }
                    onChanged();
                }
                if (ipcMessage.hasPkgName()) {
                    this.bitField0_ |= 64;
                    this.pkgName_ = ipcMessage.pkgName_;
                    onChanged();
                }
                if (ipcMessage.hasClassName()) {
                    this.bitField0_ |= 128;
                    this.className_ = ipcMessage.className_;
                    onChanged();
                }
                if (ipcMessage.hasMethodName()) {
                    this.bitField0_ |= 256;
                    this.methodName_ = ipcMessage.methodName_;
                    onChanged();
                }
                if (!ipcMessage.types_.isEmpty()) {
                    if (this.types_.isEmpty()) {
                        this.types_ = ipcMessage.types_;
                        this.bitField0_ &= -513;
                    } else {
                        ensureTypesIsMutable();
                        this.types_.addAll(ipcMessage.types_);
                    }
                    onChanged();
                }
                if (!ipcMessage.params_.isEmpty()) {
                    if (this.params_.isEmpty()) {
                        this.params_ = ipcMessage.params_;
                        this.bitField0_ &= -1025;
                    } else {
                        ensureParamsIsMutable();
                        this.params_.addAll(ipcMessage.params_);
                    }
                    onChanged();
                }
                if (ipcMessage.hasIsSyncCall()) {
                    setIsSyncCall(ipcMessage.getIsSyncCall());
                }
                if (ipcMessage.hasWaitId()) {
                    setWaitId(ipcMessage.getWaitId());
                }
                if (ipcMessage.hasRetValue()) {
                    mergeRetValue(ipcMessage.getRetValue());
                }
                if (ipcMessage.hasEncrypt()) {
                    setEncrypt(ipcMessage.getEncrypt());
                }
                if (ipcMessage.hasIsDebug()) {
                    setIsDebug(ipcMessage.getIsDebug());
                }
                mergeUnknownFields(ipcMessage.getUnknownFields());
                return this;
            }

            /* JADX WARN: Code duplicated, block: B:15:0x001d  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public final Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
                IpcMessage ipcMessage = null;
                try {
                    try {
                        IpcMessage partialFrom = IpcMessage.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                        if (partialFrom != null) {
                            mergeFrom(partialFrom);
                        }
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        IpcMessage ipcMessage2 = (IpcMessage) e.getUnfinishedMessage();
                        try {
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            ipcMessage = ipcMessage2;
                            if (ipcMessage != null) {
                                mergeFrom(ipcMessage);
                            }
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (ipcMessage != null) {
                        mergeFrom(ipcMessage);
                    }
                    throw th;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasCmd() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getCmd() {
                return this.cmd_;
            }

            public final Builder setCmd(int i) {
                this.bitField0_ |= 1;
                this.cmd_ = i;
                onChanged();
                return this;
            }

            public final Builder clearCmd() {
                this.bitField0_ &= -2;
                this.cmd_ = 0;
                onChanged();
                return this;
            }

            private void ensureArg1IsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.arg1_ = new ArrayList(this.arg1_);
                    this.bitField0_ |= 2;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final List<Integer> getArg1List() {
                return Collections.unmodifiableList(this.arg1_);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getArg1Count() {
                return this.arg1_.size();
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getArg1(int i) {
                return this.arg1_.get(i).intValue();
            }

            public final Builder setArg1(int i, int i2) {
                ensureArg1IsMutable();
                this.arg1_.set(i, Integer.valueOf(i2));
                onChanged();
                return this;
            }

            public final Builder addArg1(int i) {
                ensureArg1IsMutable();
                this.arg1_.add(Integer.valueOf(i));
                onChanged();
                return this;
            }

            public final Builder addAllArg1(Iterable<? extends Integer> iterable) {
                ensureArg1IsMutable();
                GeneratedMessage.Builder.addAll(iterable, this.arg1_);
                onChanged();
                return this;
            }

            public final Builder clearArg1() {
                this.arg1_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            private void ensureArg2IsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.arg2_ = new LazyStringArrayList(this.arg2_);
                    this.bitField0_ |= 4;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final List<String> getArg2List() {
                return Collections.unmodifiableList(this.arg2_);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getArg2Count() {
                return this.arg2_.size();
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final String getArg2(int i) {
                return this.arg2_.get(i);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ByteString getArg2Bytes(int i) {
                return this.arg2_.getByteString(i);
            }

            public final Builder setArg2(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureArg2IsMutable();
                this.arg2_.set(i, str);
                onChanged();
                return this;
            }

            public final Builder addArg2(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureArg2IsMutable();
                this.arg2_.add(str);
                onChanged();
                return this;
            }

            public final Builder addAllArg2(Iterable<String> iterable) {
                ensureArg2IsMutable();
                GeneratedMessage.Builder.addAll(iterable, this.arg2_);
                onChanged();
                return this;
            }

            public final Builder clearArg2() {
                this.arg2_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -5;
                onChanged();
                return this;
            }

            public final Builder addArg2Bytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                ensureArg2IsMutable();
                this.arg2_.add(byteString);
                onChanged();
                return this;
            }

            private void ensureArg3IsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.arg3_ = new ArrayList(this.arg3_);
                    this.bitField0_ |= 8;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final List<Float> getArg3List() {
                return Collections.unmodifiableList(this.arg3_);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getArg3Count() {
                return this.arg3_.size();
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final float getArg3(int i) {
                return this.arg3_.get(i).floatValue();
            }

            public final Builder setArg3(int i, float f) {
                ensureArg3IsMutable();
                this.arg3_.set(i, Float.valueOf(f));
                onChanged();
                return this;
            }

            public final Builder addArg3(float f) {
                ensureArg3IsMutable();
                this.arg3_.add(Float.valueOf(f));
                onChanged();
                return this;
            }

            public final Builder addAllArg3(Iterable<? extends Float> iterable) {
                ensureArg3IsMutable();
                GeneratedMessage.Builder.addAll(iterable, this.arg3_);
                onChanged();
                return this;
            }

            public final Builder clearArg3() {
                this.arg3_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasFileData() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ByteString getFileData() {
                return this.fileData_;
            }

            public final Builder setFileData(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.fileData_ = byteString;
                onChanged();
                return this;
            }

            public final Builder clearFileData() {
                this.bitField0_ &= -17;
                this.fileData_ = IpcMessage.getDefaultInstance().getFileData();
                onChanged();
                return this;
            }

            private void ensureArg4IsMutable() {
                if ((this.bitField0_ & 32) != 32) {
                    this.arg4_ = new ArrayList(this.arg4_);
                    this.bitField0_ |= 32;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final List<Long> getArg4List() {
                return Collections.unmodifiableList(this.arg4_);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getArg4Count() {
                return this.arg4_.size();
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final long getArg4(int i) {
                return this.arg4_.get(i).longValue();
            }

            public final Builder setArg4(int i, long j) {
                ensureArg4IsMutable();
                this.arg4_.set(i, Long.valueOf(j));
                onChanged();
                return this;
            }

            public final Builder addArg4(long j) {
                ensureArg4IsMutable();
                this.arg4_.add(Long.valueOf(j));
                onChanged();
                return this;
            }

            public final Builder addAllArg4(Iterable<? extends Long> iterable) {
                ensureArg4IsMutable();
                GeneratedMessage.Builder.addAll(iterable, this.arg4_);
                onChanged();
                return this;
            }

            public final Builder clearArg4() {
                this.arg4_ = Collections.emptyList();
                this.bitField0_ &= -33;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasPkgName() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final String getPkgName() {
                Object obj = this.pkgName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.pkgName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ByteString getPkgNameBytes() {
                Object obj = this.pkgName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.pkgName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public final Builder setPkgName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.pkgName_ = str;
                onChanged();
                return this;
            }

            public final Builder clearPkgName() {
                this.bitField0_ &= -65;
                this.pkgName_ = IpcMessage.getDefaultInstance().getPkgName();
                onChanged();
                return this;
            }

            public final Builder setPkgNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.pkgName_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasClassName() {
                return (this.bitField0_ & 128) == 128;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final String getClassName() {
                Object obj = this.className_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.className_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ByteString getClassNameBytes() {
                Object obj = this.className_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.className_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public final Builder setClassName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.className_ = str;
                onChanged();
                return this;
            }

            public final Builder clearClassName() {
                this.bitField0_ &= -129;
                this.className_ = IpcMessage.getDefaultInstance().getClassName();
                onChanged();
                return this;
            }

            public final Builder setClassNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.className_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasMethodName() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final String getMethodName() {
                Object obj = this.methodName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.methodName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ByteString getMethodNameBytes() {
                Object obj = this.methodName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.methodName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public final Builder setMethodName(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 256;
                this.methodName_ = str;
                onChanged();
                return this;
            }

            public final Builder clearMethodName() {
                this.bitField0_ &= -257;
                this.methodName_ = IpcMessage.getDefaultInstance().getMethodName();
                onChanged();
                return this;
            }

            public final Builder setMethodNameBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 256;
                this.methodName_ = byteString;
                onChanged();
                return this;
            }

            private void ensureTypesIsMutable() {
                if ((this.bitField0_ & 512) != 512) {
                    this.types_ = new LazyStringArrayList(this.types_);
                    this.bitField0_ |= 512;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final List<String> getTypesList() {
                return Collections.unmodifiableList(this.types_);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getTypesCount() {
                return this.types_.size();
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final String getTypes(int i) {
                return this.types_.get(i);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ByteString getTypesBytes(int i) {
                return this.types_.getByteString(i);
            }

            public final Builder setTypes(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureTypesIsMutable();
                this.types_.set(i, str);
                onChanged();
                return this;
            }

            public final Builder addTypes(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureTypesIsMutable();
                this.types_.add(str);
                onChanged();
                return this;
            }

            public final Builder addAllTypes(Iterable<String> iterable) {
                ensureTypesIsMutable();
                GeneratedMessage.Builder.addAll(iterable, this.types_);
                onChanged();
                return this;
            }

            public final Builder clearTypes() {
                this.types_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -513;
                onChanged();
                return this;
            }

            public final Builder addTypesBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                ensureTypesIsMutable();
                this.types_.add(byteString);
                onChanged();
                return this;
            }

            private void ensureParamsIsMutable() {
                if ((this.bitField0_ & 1024) != 1024) {
                    this.params_ = new LazyStringArrayList(this.params_);
                    this.bitField0_ |= 1024;
                }
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final List<String> getParamsList() {
                return Collections.unmodifiableList(this.params_);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getParamsCount() {
                return this.params_.size();
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final String getParams(int i) {
                return this.params_.get(i);
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ByteString getParamsBytes(int i) {
                return this.params_.getByteString(i);
            }

            public final Builder setParams(int i, String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureParamsIsMutable();
                this.params_.set(i, str);
                onChanged();
                return this;
            }

            public final Builder addParams(String str) {
                if (str == null) {
                    throw new NullPointerException();
                }
                ensureParamsIsMutable();
                this.params_.add(str);
                onChanged();
                return this;
            }

            public final Builder addAllParams(Iterable<String> iterable) {
                ensureParamsIsMutable();
                GeneratedMessage.Builder.addAll(iterable, this.params_);
                onChanged();
                return this;
            }

            public final Builder clearParams() {
                this.params_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -1025;
                onChanged();
                return this;
            }

            public final Builder addParamsBytes(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                ensureParamsIsMutable();
                this.params_.add(byteString);
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasIsSyncCall() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean getIsSyncCall() {
                return this.isSyncCall_;
            }

            public final Builder setIsSyncCall(boolean z) {
                this.bitField0_ |= 2048;
                this.isSyncCall_ = z;
                onChanged();
                return this;
            }

            public final Builder clearIsSyncCall() {
                this.bitField0_ &= -2049;
                this.isSyncCall_ = false;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasWaitId() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final int getWaitId() {
                return this.waitId_;
            }

            public final Builder setWaitId(int i) {
                this.bitField0_ |= 4096;
                this.waitId_ = i;
                onChanged();
                return this;
            }

            public final Builder clearWaitId() {
                this.bitField0_ &= -4097;
                this.waitId_ = 0;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasRetValue() {
                return (this.bitField0_ & 8192) == 8192;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ReturnValue getRetValue() {
                if (this.retValueBuilder_ == null) {
                    return this.retValue_;
                }
                return (ReturnValue) this.retValueBuilder_.getMessage();
            }

            public final Builder setRetValue(ReturnValue returnValue) {
                if (this.retValueBuilder_ != null) {
                    this.retValueBuilder_.setMessage(returnValue);
                } else {
                    if (returnValue == null) {
                        throw new NullPointerException();
                    }
                    this.retValue_ = returnValue;
                    onChanged();
                }
                this.bitField0_ |= 8192;
                return this;
            }

            public final Builder setRetValue(ReturnValue.Builder builder) {
                if (this.retValueBuilder_ == null) {
                    this.retValue_ = builder.build();
                    onChanged();
                } else {
                    this.retValueBuilder_.setMessage(builder.build());
                }
                this.bitField0_ |= 8192;
                return this;
            }

            public final Builder mergeRetValue(ReturnValue returnValue) {
                if (this.retValueBuilder_ == null) {
                    if ((this.bitField0_ & 8192) == 8192 && this.retValue_ != ReturnValue.getDefaultInstance()) {
                        this.retValue_ = ReturnValue.newBuilder(this.retValue_).mergeFrom(returnValue).buildPartial();
                    } else {
                        this.retValue_ = returnValue;
                    }
                    onChanged();
                } else {
                    this.retValueBuilder_.mergeFrom(returnValue);
                }
                this.bitField0_ |= 8192;
                return this;
            }

            public final Builder clearRetValue() {
                if (this.retValueBuilder_ == null) {
                    this.retValue_ = ReturnValue.getDefaultInstance();
                    onChanged();
                } else {
                    this.retValueBuilder_.clear();
                }
                this.bitField0_ &= -8193;
                return this;
            }

            public final ReturnValue.Builder getRetValueBuilder() {
                this.bitField0_ |= 8192;
                onChanged();
                return (ReturnValue.Builder) getRetValueFieldBuilder().getBuilder();
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final ReturnValueOrBuilder getRetValueOrBuilder() {
                if (this.retValueBuilder_ != null) {
                    return (ReturnValueOrBuilder) this.retValueBuilder_.getMessageOrBuilder();
                }
                return this.retValue_;
            }

            private SingleFieldBuilder<ReturnValue, ReturnValue.Builder, ReturnValueOrBuilder> getRetValueFieldBuilder() {
                if (this.retValueBuilder_ == null) {
                    this.retValueBuilder_ = new SingleFieldBuilder<>(this.retValue_, getParentForChildren(), isClean());
                    this.retValue_ = null;
                }
                return this.retValueBuilder_;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasEncrypt() {
                return (this.bitField0_ & 16384) == 16384;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean getEncrypt() {
                return this.encrypt_;
            }

            public final Builder setEncrypt(boolean z) {
                this.bitField0_ |= 16384;
                this.encrypt_ = z;
                onChanged();
                return this;
            }

            public final Builder clearEncrypt() {
                this.bitField0_ &= -16385;
                this.encrypt_ = false;
                onChanged();
                return this;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean hasIsDebug() {
                return (this.bitField0_ & 32768) == 32768;
            }

            @Override // com.cyjh.mobileanjian.ipc.share.proto.Ipc.IpcMessageOrBuilder
            public final boolean getIsDebug() {
                return this.isDebug_;
            }

            public final Builder setIsDebug(boolean z) {
                this.bitField0_ |= 32768;
                this.isDebug_ = z;
                onChanged();
                return this;
            }

            public final Builder clearIsDebug() {
                this.bitField0_ &= -32769;
                this.isDebug_ = false;
                onChanged();
                return this;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static Descriptors.FileDescriptor m754a() {
        return f1052g;
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\tipc.proto\u0012\tJCPattern\"#\n\u0006Device\u0012\f\n\u0004imei\u0018\u0001 \u0001(\t\u0012\u000b\n\u0003mac\u0018\u0002 \u0001(\t\"£\u0001\n\u000bReturnValue\u0012'\n\u0004type\u0018\u0001 \u0001(\u000e2\u0013.JCPattern.FundType:\u0004VOID\u0012\u0012\n\nvalBoolean\u0018\u0002 \u0001(\b\u0012\u000e\n\u0006valInt\u0018\u0003 \u0001(\u0005\u0012\u000f\n\u0007valLong\u0018\u0004 \u0001(\u0003\u0012\u0010\n\bvalFloat\u0018\u0005 \u0001(\u0002\u0012\u0011\n\tvalDouble\u0018\u0006 \u0001(\u0001\u0012\u0011\n\tvalString\u0018\u0007 \u0001(\t\"Å\u0002\n\nIpcMessage\u0012\u000e\n\u0003cmd\u0018\u0001 \u0001(\u0005:\u00010\u0012\f\n\u0004arg1\u0018\u0002 \u0003(\u0005\u0012\f\n\u0004arg2\u0018\u0003 \u0003(\t\u0012\f\n\u0004arg3\u0018\u0004 \u0003(\u0002\u0012\u0010\n\bfileData\u0018\u0005 \u0001(\f\u0012\f\n\u0004arg4\u0018\u0006 \u0003(\u0003\u0012\u000f\n\u0007pkgName\u0018\u0007 \u0001(\t\u0012\u0011\n\tclassName\u0018\b \u0001(\t\u0012\u0012\n\nmethodName\u0018\t \u0001(\t\u0012\r\n\u0005types\u0018\n \u0003(", "\t\u0012\u000e\n\u0006params\u0018\u000b \u0003(\t\u0012\u0019\n\nisSyncCall\u0018\f \u0001(\b:\u0005false\u0012\u0011\n\u0006waitId\u0018\r \u0001(\u0005:\u00010\u0012(\n\bretValue\u0018\u000e \u0001(\u000b2\u0016.JCPattern.ReturnValue\u0012\u0016\n\u0007encrypt\u0018\u000f \u0001(\b:\u0005false\u0012\u0016\n\u0007isDebug\u0018\u0010 \u0001(\b:\u0005false*W\n\bFundType\u0012\b\n\u0004VOID\u0010\u0001\u0012\u000b\n\u0007BOOLEAN\u0010\u0002\u0012\u0007\n\u0003INT\u0010\u0003\u0012\b\n\u0004LONG\u0010\u0004\u0012\t\n\u0005FLOAT\u0010\u0005\u0012\n\n\u0006DOUBLE\u0010\u0006\u0012\n\n\u0006STRING\u0010\u0007B&\n\u001fcom.cyjh.mobileanjian.ipc.protoB\u0003Ipc"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.cyjh.mobileanjian.ipc.share.proto.Ipc.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public final ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Ipc.f1052g = fileDescriptor;
                Descriptors.Descriptor unused2 = Ipc.f1046a = Ipc.m754a().getMessageTypes().get(0);
                GeneratedMessage.FieldAccessorTable unused3 = Ipc.f1047b = new GeneratedMessage.FieldAccessorTable(Ipc.f1046a, new String[]{"Imei", "Mac"});
                Descriptors.Descriptor unused4 = Ipc.f1048c = Ipc.m754a().getMessageTypes().get(1);
                GeneratedMessage.FieldAccessorTable unused5 = Ipc.f1049d = new GeneratedMessage.FieldAccessorTable(Ipc.f1048c, new String[]{URLConstant.TOKEN_PARAM_TYPE, "ValBoolean", "ValInt", "ValLong", "ValFloat", "ValDouble", "ValString"});
                Descriptors.Descriptor unused6 = Ipc.f1050e = Ipc.m754a().getMessageTypes().get(2);
                GeneratedMessage.FieldAccessorTable unused7 = Ipc.f1051f = new GeneratedMessage.FieldAccessorTable(Ipc.f1050e, new String[]{"Cmd", "Arg1", "Arg2", "Arg3", "FileData", "Arg4", "PkgName", "ClassName", "MethodName", "Types", "Params", "IsSyncCall", "WaitId", "RetValue", "Encrypt", "IsDebug"});
                return null;
            }
        });
    }
}
