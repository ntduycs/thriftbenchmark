package com.thanhduybk.thrift_benchmark.generated;

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import java.util.*;

public class BenchRequest implements org.apache.thrift.TBase<BenchRequest, BenchRequest._Fields>, java.io.Serializable, Cloneable, Comparable<BenchRequest> {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BenchRequest");

    private static final org.apache.thrift.protocol.TField RESPONSE_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("responseSize", org.apache.thrift.protocol.TType.I32, (short) 1);
    private static final org.apache.thrift.protocol.TField PAUSE_TIME_IN_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("pauseTimeInMs", org.apache.thrift.protocol.TType.I64, (short) 2);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<>();

    static {
        schemes.put(StandardScheme.class, new BenchRequestStandardSchemeFactory());
        schemes.put(TupleScheme.class, new BenchRequestTupleSchemeFactory());
    }

    public int responseSize; // required
    public long pauseTimeInMs; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        RESPONSE_SIZE((short) 1, "responseSize"),
        PAUSE_TIME_IN_MS((short) 2, "pauseTimeInMs");

        private static final Map<String, _Fields> byName = new HashMap<>();

        static {
            for (_Fields field : EnumSet.allOf(_Fields.class)) {
                byName.put(field.getFieldName(), field);
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, or null if its not found.
         */
        public static _Fields findByThriftId(int fieldId) {
            switch (fieldId) {
                case 1: // RESPONSE_SIZE
                    return RESPONSE_SIZE;
                case 2: // PAUSE_TIME_IN_MS
                    return PAUSE_TIME_IN_MS;
                default:
                    return null;
            }
        }

        /**
         * Find the _Fields constant that matches fieldId, throwing an exception
         * if it is not found.
         */
        public static _Fields findByThriftIdOrThrow(int fieldId) {
            _Fields fields = findByThriftId(fieldId);
            if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
            return fields;
        }

        /**
         * Find the _Fields constant that matches name, or null if its not found.
         */
        public static _Fields findByName(String name) {
            return byName.get(name);
        }

        private final short _thriftId;
        private final String _fieldName;

        _Fields(short thriftId, String fieldName) {
            _thriftId = thriftId;
            _fieldName = fieldName;
        }

        public short getThriftFieldId() {
            return _thriftId;
        }

        public String getFieldName() {
            return _fieldName;
        }
    }

    // isset id assignments
    private static final int __RESPONSESIZE_ISSET_ID = 0;
    private static final int __PAUSETIMEINMS_ISSET_ID = 1;
    private byte __isset_bitfield = 0;
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<>(_Fields.class);
        tmpMap.put(_Fields.RESPONSE_SIZE, new org.apache.thrift.meta_data.FieldMetaData("responseSize", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
        tmpMap.put(_Fields.PAUSE_TIME_IN_MS, new org.apache.thrift.meta_data.FieldMetaData("pauseTimeInMs", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BenchRequest.class, metaDataMap);
    }

    public BenchRequest() {
    }

    public BenchRequest(
            int responseSize,
            long pauseTimeInMs) {
        this();
        this.responseSize = responseSize;
        setResponseSizeIsSet(true);
        this.pauseTimeInMs = pauseTimeInMs;
        setPauseTimeInMsIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public BenchRequest(BenchRequest other) {
        __isset_bitfield = other.__isset_bitfield;
        this.responseSize = other.responseSize;
        this.pauseTimeInMs = other.pauseTimeInMs;
    }

    public BenchRequest deepCopy() {
        return new BenchRequest(this);
    }

    @Override
    public void clear() {
        setResponseSizeIsSet(false);
        this.responseSize = 0;
        setPauseTimeInMsIsSet(false);
        this.pauseTimeInMs = 0;
    }

    public int getResponseSize() {
        return this.responseSize;
    }

    public BenchRequest setResponseSize(int responseSize) {
        this.responseSize = responseSize;
        setResponseSizeIsSet(true);
        return this;
    }

    public void unsetResponseSize() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RESPONSESIZE_ISSET_ID);
    }

    /** Returns true if field responseSize is set (has been assigned a value) and false otherwise */
    public boolean isSetResponseSize() {
        return EncodingUtils.testBit(__isset_bitfield, __RESPONSESIZE_ISSET_ID);
    }

    public void setResponseSizeIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RESPONSESIZE_ISSET_ID, value);
    }

    public long getPauseTimeInMs() {
        return this.pauseTimeInMs;
    }

    public BenchRequest setPauseTimeInMs(long pauseTimeInMs) {
        this.pauseTimeInMs = pauseTimeInMs;
        setPauseTimeInMsIsSet(true);
        return this;
    }

    public void unsetPauseTimeInMs() {
        __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PAUSETIMEINMS_ISSET_ID);
    }

    /** Returns true if field pauseTimeInMs is set (has been assigned a value) and false otherwise */
    public boolean isSetPauseTimeInMs() {
        return EncodingUtils.testBit(__isset_bitfield, __PAUSETIMEINMS_ISSET_ID);
    }

    public void setPauseTimeInMsIsSet(boolean value) {
        __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PAUSETIMEINMS_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (field) {
            case RESPONSE_SIZE:
                if (value == null) {
                    unsetResponseSize();
                } else {
                    setResponseSize((Integer) value);
                }
                break;

            case PAUSE_TIME_IN_MS:
                if (value == null) {
                    unsetPauseTimeInMs();
                } else {
                    setPauseTimeInMs((Long) value);
                }
                break;

        }
    }

    public Object getFieldValue(_Fields field) {
        switch (field) {
            case RESPONSE_SIZE:
                return getResponseSize();

            case PAUSE_TIME_IN_MS:
                return getPauseTimeInMs();

        }
        throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case RESPONSE_SIZE:
                return isSetResponseSize();
            case PAUSE_TIME_IN_MS:
                return isSetPauseTimeInMs();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof BenchRequest)
            return this.equals((BenchRequest) that);
        return false;
    }

    public boolean equals(BenchRequest that) {
        if (that == null)
            return false;

        boolean this_present_responseSize = true;
        boolean that_present_responseSize = true;
        if (this_present_responseSize || that_present_responseSize) {
            if (!(this_present_responseSize && that_present_responseSize))
                return false;
            if (this.responseSize != that.responseSize)
                return false;
        }

        boolean this_present_pauseTimeInMs = true;
        boolean that_present_pauseTimeInMs = true;
        if (this_present_pauseTimeInMs || that_present_pauseTimeInMs) {
            if (!(this_present_pauseTimeInMs && that_present_pauseTimeInMs))
                return false;
            return this.pauseTimeInMs == that.pauseTimeInMs;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(BenchRequest other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = Boolean.compare(isSetResponseSize(), other.isSetResponseSize());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetResponseSize()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.responseSize, other.responseSize);
            if (lastComparison != 0) {
                return lastComparison;
            }
        }
        lastComparison = Boolean.compare(isSetPauseTimeInMs(), other.isSetPauseTimeInMs());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetPauseTimeInMs()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pauseTimeInMs, other.pauseTimeInMs);
            return lastComparison;
        }
        return 0;
    }

    public _Fields fieldForId(int fieldId) {
        return _Fields.findByThriftId(fieldId);
    }

    public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
        schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
        schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BenchRequest(");
        boolean first = true;

        sb.append("responseSize:");
        sb.append(this.responseSize);
        first = false;
        if (!first) sb.append(", ");
        sb.append("pauseTimeInMs:");
        sb.append(this.pauseTimeInMs);
        first = false;
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
        // check for required fields
        // check for sub-struct validity
    }

    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        try {
            write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
        try {
            // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
            __isset_bitfield = 0;
            read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private static class BenchRequestStandardSchemeFactory implements SchemeFactory {
        public BenchRequestStandardScheme getScheme() {
            return new BenchRequestStandardScheme();
        }
    }

    private static class BenchRequestStandardScheme extends StandardScheme<BenchRequest> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, BenchRequest struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // RESPONSE_SIZE
                        if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
                            struct.responseSize = iprot.readI32();
                            struct.setResponseSizeIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    case 2: // PAUSE_TIME_IN_MS
                        if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
                            struct.pauseTimeInMs = iprot.readI64();
                            struct.setPauseTimeInMsIsSet(true);
                        } else {
                            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                        }
                        break;
                    default:
                        org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
                }
                iprot.readFieldEnd();
            }
            iprot.readStructEnd();

            // check for required fields of primitive type, which can't be checked in the validate method
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, BenchRequest struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            oprot.writeFieldBegin(RESPONSE_SIZE_FIELD_DESC);
            oprot.writeI32(struct.responseSize);
            oprot.writeFieldEnd();
            oprot.writeFieldBegin(PAUSE_TIME_IN_MS_FIELD_DESC);
            oprot.writeI64(struct.pauseTimeInMs);
            oprot.writeFieldEnd();
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class BenchRequestTupleSchemeFactory implements SchemeFactory {
        public BenchRequestTupleScheme getScheme() {
            return new BenchRequestTupleScheme();
        }
    }

    private static class BenchRequestTupleScheme extends TupleScheme<BenchRequest> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, BenchRequest struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            BitSet optionals = new BitSet();
            if (struct.isSetResponseSize()) {
                optionals.set(0);
            }
            if (struct.isSetPauseTimeInMs()) {
                optionals.set(1);
            }
            oprot.writeBitSet(optionals, 2);
            if (struct.isSetResponseSize()) {
                oprot.writeI32(struct.responseSize);
            }
            if (struct.isSetPauseTimeInMs()) {
                oprot.writeI64(struct.pauseTimeInMs);
            }
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, BenchRequest struct) throws org.apache.thrift.TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            BitSet incoming = iprot.readBitSet(2);
            if (incoming.get(0)) {
                struct.responseSize = iprot.readI32();
                struct.setResponseSizeIsSet(true);
            }
            if (incoming.get(1)) {
                struct.pauseTimeInMs = iprot.readI64();
                struct.setPauseTimeInMsIsSet(true);
            }
        }
    }

}

