package com.thanhduybk.thrift_benchmark.generated;

import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import java.util.*;

public class BenchResponse implements org.apache.thrift.TBase<BenchResponse, BenchResponse._Fields>, java.io.Serializable, Cloneable, Comparable<BenchResponse> {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BenchResponse");

    private static final org.apache.thrift.protocol.TField RESPONSE_STRING_FIELD_DESC = new org.apache.thrift.protocol.TField("responseString", org.apache.thrift.protocol.TType.STRING, (short) 1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<>();

    static {
        schemes.put(StandardScheme.class, new BenchResponseStandardSchemeFactory());
        schemes.put(TupleScheme.class, new BenchResponseTupleSchemeFactory());
    }

    public String responseString; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        RESPONSE_STRING((short) 1, "responseString");

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
            if (fieldId == 1) { // RESPONSE_STRING
                return RESPONSE_STRING;
            }
            return null;
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
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<>(_Fields.class);
        tmpMap.put(_Fields.RESPONSE_STRING, new org.apache.thrift.meta_data.FieldMetaData("responseString", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BenchResponse.class, metaDataMap);
    }

    public BenchResponse() {
    }

    public BenchResponse(
            String responseString) {
        this();
        this.responseString = responseString;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public BenchResponse(BenchResponse other) {
        if (other.isSetResponseString()) {
            this.responseString = other.responseString;
        }
    }

    public BenchResponse deepCopy() {
        return new BenchResponse(this);
    }

    @Override
    public void clear() {
        this.responseString = null;
    }

    public String getResponseString() {
        return this.responseString;
    }

    public BenchResponse setResponseString(String responseString) {
        this.responseString = responseString;
        return this;
    }

    public void unsetResponseString() {
        this.responseString = null;
    }

    /** Returns true if field responseString is set (has been assigned a value) and false otherwise */
    public boolean isSetResponseString() {
        return this.responseString != null;
    }

    public void setResponseStringIsSet(boolean value) {
        if (!value) {
            this.responseString = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        if (field == _Fields.RESPONSE_STRING) {
            if (value == null) {
                unsetResponseString();
            } else {
                setResponseString((String) value);
            }
        }
    }

    public Object getFieldValue(_Fields field) {
        if (field == _Fields.RESPONSE_STRING) {
            return getResponseString();
        }
        throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        if (field == _Fields.RESPONSE_STRING) {
            return isSetResponseString();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof BenchResponse)
            return this.equals((BenchResponse) that);
        return false;
    }

    public boolean equals(BenchResponse that) {
        if (that == null)
            return false;

        boolean this_present_responseString = this.isSetResponseString();
        boolean that_present_responseString = that.isSetResponseString();
        if (this_present_responseString || that_present_responseString) {
            if (!(this_present_responseString && that_present_responseString))
                return false;
            return this.responseString.equals(that.responseString);
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(BenchResponse other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = Boolean.compare(isSetResponseString(), other.isSetResponseString());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetResponseString()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.responseString, other.responseString);
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
        StringBuilder sb = new StringBuilder("BenchResponse(");
        boolean first = true;

        sb.append("responseString:");
        sb.append(Objects.requireNonNullElse(this.responseString, "null"));
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
            read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
        } catch (org.apache.thrift.TException te) {
            throw new java.io.IOException(te);
        }
    }

    private static class BenchResponseStandardSchemeFactory implements SchemeFactory {
        public BenchResponseStandardScheme getScheme() {
            return new BenchResponseStandardScheme();
        }
    }

    private static class BenchResponseStandardScheme extends StandardScheme<BenchResponse> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, BenchResponse struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                if (schemeField.id == 1) { // RESPONSE_STRING
                    if (schemeField.type == TType.STRING) {
                        struct.responseString = iprot.readString();
                        struct.setResponseStringIsSet(true);
                    } else {
                        TProtocolUtil.skip(iprot, schemeField.type);
                    }
                } else {
                    TProtocolUtil.skip(iprot, schemeField.type);
                }
                iprot.readFieldEnd();
            }
            iprot.readStructEnd();

            // check for required fields of primitive type, which can't be checked in the validate method
            struct.validate();
        }

        public void write(org.apache.thrift.protocol.TProtocol oprot, BenchResponse struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            if (struct.responseString != null) {
                oprot.writeFieldBegin(RESPONSE_STRING_FIELD_DESC);
                oprot.writeString(struct.responseString);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class BenchResponseTupleSchemeFactory implements SchemeFactory {
        public BenchResponseTupleScheme getScheme() {
            return new BenchResponseTupleScheme();
        }
    }

    private static class BenchResponseTupleScheme extends TupleScheme<BenchResponse> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, BenchResponse struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            BitSet optionals = new BitSet();
            if (struct.isSetResponseString()) {
                optionals.set(0);
            }
            oprot.writeBitSet(optionals, 1);
            if (struct.isSetResponseString()) {
                oprot.writeString(struct.responseString);
            }
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, BenchResponse struct) throws org.apache.thrift.TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
                struct.responseString = iprot.readString();
                struct.setResponseStringIsSet(true);
            }
        }
    }

}

