/**
 * Autogenerated by Thrift Compiler (0.9.1)
 * <p>
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package com.thanhduybk.thrift_testing.generated;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;

public class SampleResponse implements org.apache.thrift.TBase<SampleResponse, SampleResponse._Fields>, java.io.Serializable, Cloneable, Comparable<SampleResponse> {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SampleResponse");

    private static final org.apache.thrift.protocol.TField RES_STR_FIELD_DESC = new org.apache.thrift.protocol.TField("resStr", org.apache.thrift.protocol.TType.STRING, (short) 1);

    private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();

    static {
        schemes.put(StandardScheme.class, new SampleResponseStandardSchemeFactory());
        schemes.put(TupleScheme.class, new SampleResponseTupleSchemeFactory());
    }

    public String resStr; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
        RES_STR((short) 1, "resStr");

        private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

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
                case 1: // RES_STR
                    return RES_STR;
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
    public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;

    static {
        Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
        tmpMap.put(_Fields.RES_STR, new org.apache.thrift.meta_data.FieldMetaData("resStr", org.apache.thrift.TFieldRequirementType.DEFAULT,
                new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
        metaDataMap = Collections.unmodifiableMap(tmpMap);
        org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SampleResponse.class, metaDataMap);
    }

    public SampleResponse() {
    }

    public SampleResponse(
            String resStr) {
        this();
        this.resStr = resStr;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public SampleResponse(SampleResponse other) {
        if (other.isSetResStr()) {
            this.resStr = other.resStr;
        }
    }

    public SampleResponse deepCopy() {
        return new SampleResponse(this);
    }

    @Override
    public void clear() {
        this.resStr = null;
    }

    public String getResStr() {
        return this.resStr;
    }

    public SampleResponse setResStr(String resStr) {
        this.resStr = resStr;
        return this;
    }

    public void unsetResStr() {
        this.resStr = null;
    }

    /** Returns true if field resStr is set (has been assigned a value) and false otherwise */
    public boolean isSetResStr() {
        return this.resStr != null;
    }

    public void setResStrIsSet(boolean value) {
        if (!value) {
            this.resStr = null;
        }
    }

    public void setFieldValue(_Fields field, Object value) {
        switch (field) {
            case RES_STR:
                if (value == null) {
                    unsetResStr();
                } else {
                    setResStr((String) value);
                }
                break;

        }
    }

    public Object getFieldValue(_Fields field) {
        switch (field) {
            case RES_STR:
                return getResStr();

        }
        throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }

        switch (field) {
            case RES_STR:
                return isSetResStr();
        }
        throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null)
            return false;
        if (that instanceof SampleResponse)
            return this.equals((SampleResponse) that);
        return false;
    }

    public boolean equals(SampleResponse that) {
        if (that == null)
            return false;

        boolean this_present_resStr = true && this.isSetResStr();
        boolean that_present_resStr = true && that.isSetResStr();
        if (this_present_resStr || that_present_resStr) {
            if (!(this_present_resStr && that_present_resStr))
                return false;
            if (!this.resStr.equals(that.resStr))
                return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(SampleResponse other) {
        if (!getClass().equals(other.getClass())) {
            return getClass().getName().compareTo(other.getClass().getName());
        }

        int lastComparison = 0;

        lastComparison = Boolean.valueOf(isSetResStr()).compareTo(other.isSetResStr());
        if (lastComparison != 0) {
            return lastComparison;
        }
        if (isSetResStr()) {
            lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resStr, other.resStr);
            if (lastComparison != 0) {
                return lastComparison;
            }
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
        StringBuilder sb = new StringBuilder("SampleResponse(");
        boolean first = true;

        sb.append("resStr:");
        if (this.resStr == null) {
            sb.append("null");
        } else {
            sb.append(this.resStr);
        }
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

    private static class SampleResponseStandardSchemeFactory implements SchemeFactory {
        public SampleResponseStandardScheme getScheme() {
            return new SampleResponseStandardScheme();
        }
    }

    private static class SampleResponseStandardScheme extends StandardScheme<SampleResponse> {

        public void read(org.apache.thrift.protocol.TProtocol iprot, SampleResponse struct) throws org.apache.thrift.TException {
            org.apache.thrift.protocol.TField schemeField;
            iprot.readStructBegin();
            while (true) {
                schemeField = iprot.readFieldBegin();
                if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                    break;
                }
                switch (schemeField.id) {
                    case 1: // RES_STR
                        if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
                            struct.resStr = iprot.readString();
                            struct.setResStrIsSet(true);
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

        public void write(org.apache.thrift.protocol.TProtocol oprot, SampleResponse struct) throws org.apache.thrift.TException {
            struct.validate();

            oprot.writeStructBegin(STRUCT_DESC);
            if (struct.resStr != null) {
                oprot.writeFieldBegin(RES_STR_FIELD_DESC);
                oprot.writeString(struct.resStr);
                oprot.writeFieldEnd();
            }
            oprot.writeFieldStop();
            oprot.writeStructEnd();
        }

    }

    private static class SampleResponseTupleSchemeFactory implements SchemeFactory {
        public SampleResponseTupleScheme getScheme() {
            return new SampleResponseTupleScheme();
        }
    }

    private static class SampleResponseTupleScheme extends TupleScheme<SampleResponse> {

        @Override
        public void write(org.apache.thrift.protocol.TProtocol prot, SampleResponse struct) throws org.apache.thrift.TException {
            TTupleProtocol oprot = (TTupleProtocol) prot;
            BitSet optionals = new BitSet();
            if (struct.isSetResStr()) {
                optionals.set(0);
            }
            oprot.writeBitSet(optionals, 1);
            if (struct.isSetResStr()) {
                oprot.writeString(struct.resStr);
            }
        }

        @Override
        public void read(org.apache.thrift.protocol.TProtocol prot, SampleResponse struct) throws org.apache.thrift.TException {
            TTupleProtocol iprot = (TTupleProtocol) prot;
            BitSet incoming = iprot.readBitSet(1);
            if (incoming.get(0)) {
                struct.resStr = iprot.readString();
                struct.setResStrIsSet(true);
            }
        }
    }

}

