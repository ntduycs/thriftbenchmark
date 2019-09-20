package com.thanhduybk.thrift_benchmark.generated;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TType;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BenchService {

    public interface Iface {

        BenchResponse doBenchmark(BenchRequest req) throws org.apache.thrift.TException;

    }

    public interface AsyncIface {

        void doBenchmark(BenchRequest req, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException;

    }

    public static class Client extends org.apache.thrift.TServiceClient implements Iface {
        public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
            public Factory() {
            }

            public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
                return new Client(prot);
            }

            public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
                return new Client(iprot, oprot);
            }
        }

        public Client(org.apache.thrift.protocol.TProtocol prot) {
            super(prot, prot);
        }

        public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
            super(iprot, oprot);
        }

        public BenchResponse doBenchmark(BenchRequest req) throws org.apache.thrift.TException {
            send_doBenchmark(req);
            return recv_doBenchmark();
        }

        public void send_doBenchmark(BenchRequest req) throws org.apache.thrift.TException {
            doBenchmark_args args = new doBenchmark_args();
            args.setReq(req);
            sendBase("doBenchmark", args);
        }

        public BenchResponse recv_doBenchmark() throws org.apache.thrift.TException {
            doBenchmark_result result = new doBenchmark_result();
            receiveBase(result, "doBenchmark");
            if (result.isSetSuccess()) {
                return result.success;
            }
            throw new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.MISSING_RESULT, "doBenchmark failed: unknown result");
        }

    }

    public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
        public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
            private org.apache.thrift.async.TAsyncClientManager clientManager;
            private org.apache.thrift.protocol.TProtocolFactory protocolFactory;

            public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
                this.clientManager = clientManager;
                this.protocolFactory = protocolFactory;
            }

            public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
                return new AsyncClient(protocolFactory, clientManager, transport);
            }
        }

        public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
            super(protocolFactory, clientManager, transport);
        }

        public void doBenchmark(BenchRequest req, org.apache.thrift.async.AsyncMethodCallback resultHandler) throws org.apache.thrift.TException {
            checkReady();
            doBenchmark_call method_call = new doBenchmark_call(req, resultHandler, this, ___protocolFactory, ___transport);
            this.___currentMethod = method_call;
            ___manager.call(method_call);
        }

        public static class doBenchmark_call extends org.apache.thrift.async.TAsyncMethodCall {
            private BenchRequest req;

            public doBenchmark_call(BenchRequest req, org.apache.thrift.async.AsyncMethodCallback resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
                super(client, protocolFactory, transport, resultHandler, false);
                this.req = req;
            }

            public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
                prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("doBenchmark", org.apache.thrift.protocol.TMessageType.CALL, 0));
                doBenchmark_args args = new doBenchmark_args();
                args.setReq(req);
                args.write(prot);
                prot.writeMessageEnd();
            }

            public BenchResponse getResult() throws org.apache.thrift.TException {
                if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
                    throw new IllegalStateException("Method call not finished!");
                }
                org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
                org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
                return (new Client(prot)).recv_doBenchmark();
            }
        }

    }

    public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
        private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());

        public Processor(I iface) {
            super(iface, getProcessMap(new HashMap<>()));
        }

        protected Processor(I iface, Map<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
            super(iface, getProcessMap(processMap));
        }

        private static <I extends Iface> Map<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> getProcessMap(Map<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
            processMap.put("doBenchmark", new doBenchmark());
            return processMap;
        }

        public static class doBenchmark<I extends Iface> extends org.apache.thrift.ProcessFunction<I, doBenchmark_args> {
            public doBenchmark() {
                super("doBenchmark");
            }

            public doBenchmark_args getEmptyArgsInstance() {
                return new doBenchmark_args();
            }

            protected boolean isOneway() {
                return false;
            }

            public doBenchmark_result getResult(I iface, doBenchmark_args args) throws org.apache.thrift.TException {
                doBenchmark_result result = new doBenchmark_result();
                result.success = iface.doBenchmark(args.req);
                return result;
            }
        }

    }

    public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
        private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());

        public AsyncProcessor(I iface) {
            super(iface, getProcessMap(new HashMap<>()));
        }

        protected AsyncProcessor(I iface, Map<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>> processMap) {
            super(iface, getProcessMap(processMap));
        }

        private static <I extends AsyncIface> Map<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>> getProcessMap(Map<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>> processMap) {
            processMap.put("doBenchmark", new doBenchmark());
            return processMap;
        }

        public static class doBenchmark<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, doBenchmark_args, BenchResponse> {
            public doBenchmark() {
                super("doBenchmark");
            }

            public doBenchmark_args getEmptyArgsInstance() {
                return new doBenchmark_args();
            }

            public AsyncMethodCallback<BenchResponse> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
                final org.apache.thrift.AsyncProcessFunction fcall = this;
                return new AsyncMethodCallback<>() {
                    public void onComplete(BenchResponse o) {
                        doBenchmark_result result = new doBenchmark_result();
                        result.success = o;
                        try {
                            fcall.sendResponse(fb, result, org.apache.thrift.protocol.TMessageType.REPLY, seqid);
                            return;
                        } catch (Exception e) {
                            LOGGER.error("Exception writing to internal frame buffer", e);
                        }
                        fb.close();
                    }

                    public void onError(Exception e) {
                        byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
                        org.apache.thrift.TBase msg;
                        doBenchmark_result result = new doBenchmark_result();
                        {
                            msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
                            msg = (org.apache.thrift.TBase) new org.apache.thrift.TApplicationException(org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
                        }
                        try {
                            fcall.sendResponse(fb, msg, msgType, seqid);
                            return;
                        } catch (Exception ex) {
                            LOGGER.error("Exception writing to internal frame buffer", ex);
                        }
                        fb.close();
                    }
                };
            }

            protected boolean isOneway() {
                return false;
            }

            public void start(I iface, doBenchmark_args args, org.apache.thrift.async.AsyncMethodCallback<BenchResponse> resultHandler) throws TException {
                iface.doBenchmark(args.req, resultHandler);
            }
        }

    }

    public static class doBenchmark_args implements org.apache.thrift.TBase<doBenchmark_args, doBenchmark_args._Fields>, java.io.Serializable, Cloneable, Comparable<doBenchmark_args> {
        private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("doBenchmark_args");

        private static final org.apache.thrift.protocol.TField REQ_FIELD_DESC = new org.apache.thrift.protocol.TField("req", org.apache.thrift.protocol.TType.STRUCT, (short) 1);

        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<>();

        static {
            schemes.put(StandardScheme.class, new doBenchmark_argsStandardSchemeFactory());
            schemes.put(TupleScheme.class, new doBenchmark_argsTupleSchemeFactory());
        }

        public BenchRequest req; // required

        /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
        public enum _Fields implements org.apache.thrift.TFieldIdEnum {
            REQ((short) 1, "req");

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
                if (fieldId == 1) { // REQ
                    return REQ;
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
            tmpMap.put(_Fields.REQ, new org.apache.thrift.meta_data.FieldMetaData("req", org.apache.thrift.TFieldRequirementType.DEFAULT,
                    new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, BenchRequest.class)));
            metaDataMap = Collections.unmodifiableMap(tmpMap);
            org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(doBenchmark_args.class, metaDataMap);
        }

        public doBenchmark_args() {
        }

        public doBenchmark_args(
                BenchRequest req) {
            this();
            this.req = req;
        }

        /**
         * Performs a deep copy on <i>other</i>.
         */
        public doBenchmark_args(doBenchmark_args other) {
            if (other.isSetReq()) {
                this.req = new BenchRequest(other.req);
            }
        }

        public doBenchmark_args deepCopy() {
            return new doBenchmark_args(this);
        }

        @Override
        public void clear() {
            this.req = null;
        }

        public BenchRequest getReq() {
            return this.req;
        }

        public doBenchmark_args setReq(BenchRequest req) {
            this.req = req;
            return this;
        }

        public void unsetReq() {
            this.req = null;
        }

        /** Returns true if field req is set (has been assigned a value) and false otherwise */
        public boolean isSetReq() {
            return this.req != null;
        }

        public void setReqIsSet(boolean value) {
            if (!value) {
                this.req = null;
            }
        }

        public void setFieldValue(_Fields field, Object value) {
            if (field == _Fields.REQ) {
                if (value == null) {
                    unsetReq();
                } else {
                    setReq((BenchRequest) value);
                }
            }
        }

        public Object getFieldValue(_Fields field) {
            if (field == _Fields.REQ) {
                return getReq();
            }
            throw new IllegalStateException();
        }

        /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
        public boolean isSet(_Fields field) {
            if (field == null) {
                throw new IllegalArgumentException();
            }

            if (field == _Fields.REQ) {
                return isSetReq();
            }
            throw new IllegalStateException();
        }

        @Override
        public boolean equals(Object that) {
            if (that == null)
                return false;
            if (that instanceof doBenchmark_args)
                return this.equals((doBenchmark_args) that);
            return false;
        }

        public boolean equals(doBenchmark_args that) {
            if (that == null)
                return false;

            boolean this_present_req = this.isSetReq();
            boolean that_present_req = that.isSetReq();
            if (this_present_req || that_present_req) {
                if (!(this_present_req && that_present_req))
                    return false;
                return this.req.equals(that.req);
            }

            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public int compareTo(doBenchmark_args other) {
            if (!getClass().equals(other.getClass())) {
                return getClass().getName().compareTo(other.getClass().getName());
            }

            int lastComparison = 0;

            lastComparison = Boolean.compare(isSetReq(), other.isSetReq());
            if (lastComparison != 0) {
                return lastComparison;
            }
            if (isSetReq()) {
                lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.req, other.req);
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
            StringBuilder sb = new StringBuilder("doBenchmark_args(");
            boolean first = true;

            sb.append("req:");
            if (this.req == null) {
                sb.append("null");
            } else {
                sb.append(this.req);
            }
            first = false;
            sb.append(")");
            return sb.toString();
        }

        public void validate() throws org.apache.thrift.TException {
            // check for required fields
            // check for sub-struct validity
            if (req != null) {
                req.validate();
            }
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

        private static class doBenchmark_argsStandardSchemeFactory implements SchemeFactory {
            public doBenchmark_argsStandardScheme getScheme() {
                return new doBenchmark_argsStandardScheme();
            }
        }

        private static class doBenchmark_argsStandardScheme extends StandardScheme<doBenchmark_args> {

            public void read(org.apache.thrift.protocol.TProtocol iprot, doBenchmark_args struct) throws org.apache.thrift.TException {
                org.apache.thrift.protocol.TField schemeField;
                iprot.readStructBegin();
                while (true) {
                    schemeField = iprot.readFieldBegin();
                    if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                        break;
                    }
                    if (schemeField.id == 1) { // REQ
                        if (schemeField.type == TType.STRUCT) {
                            struct.req = new BenchRequest();
                            struct.req.read(iprot);
                            struct.setReqIsSet(true);
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

            public void write(org.apache.thrift.protocol.TProtocol oprot, doBenchmark_args struct) throws org.apache.thrift.TException {
                struct.validate();

                oprot.writeStructBegin(STRUCT_DESC);
                if (struct.req != null) {
                    oprot.writeFieldBegin(REQ_FIELD_DESC);
                    struct.req.write(oprot);
                    oprot.writeFieldEnd();
                }
                oprot.writeFieldStop();
                oprot.writeStructEnd();
            }

        }

        private static class doBenchmark_argsTupleSchemeFactory implements SchemeFactory {
            public doBenchmark_argsTupleScheme getScheme() {
                return new doBenchmark_argsTupleScheme();
            }
        }

        private static class doBenchmark_argsTupleScheme extends TupleScheme<doBenchmark_args> {

            @Override
            public void write(org.apache.thrift.protocol.TProtocol prot, doBenchmark_args struct) throws org.apache.thrift.TException {
                TTupleProtocol oprot = (TTupleProtocol) prot;
                BitSet optionals = new BitSet();
                if (struct.isSetReq()) {
                    optionals.set(0);
                }
                oprot.writeBitSet(optionals, 1);
                if (struct.isSetReq()) {
                    struct.req.write(oprot);
                }
            }

            @Override
            public void read(org.apache.thrift.protocol.TProtocol prot, doBenchmark_args struct) throws org.apache.thrift.TException {
                TTupleProtocol iprot = (TTupleProtocol) prot;
                BitSet incoming = iprot.readBitSet(1);
                if (incoming.get(0)) {
                    struct.req = new BenchRequest();
                    struct.req.read(iprot);
                    struct.setReqIsSet(true);
                }
            }
        }

    }

    public static class doBenchmark_result implements org.apache.thrift.TBase<doBenchmark_result, doBenchmark_result._Fields>, java.io.Serializable, Cloneable, Comparable<doBenchmark_result> {
        private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("doBenchmark_result");

        private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField("success", org.apache.thrift.protocol.TType.STRUCT, (short) 0);

        private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<>();

        static {
            schemes.put(StandardScheme.class, new doBenchmark_resultStandardSchemeFactory());
            schemes.put(TupleScheme.class, new doBenchmark_resultTupleSchemeFactory());
        }

        public BenchResponse success; // required

        /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
        public enum _Fields implements org.apache.thrift.TFieldIdEnum {
            SUCCESS((short) 0, "success");

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
                if (fieldId == 0) { // SUCCESS
                    return SUCCESS;
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
            tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success", org.apache.thrift.TFieldRequirementType.DEFAULT,
                    new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, BenchResponse.class)));
            metaDataMap = Collections.unmodifiableMap(tmpMap);
            org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(doBenchmark_result.class, metaDataMap);
        }

        public doBenchmark_result() {
        }

        public doBenchmark_result(
                BenchResponse success) {
            this();
            this.success = success;
        }

        /**
         * Performs a deep copy on <i>other</i>.
         */
        public doBenchmark_result(doBenchmark_result other) {
            if (other.isSetSuccess()) {
                this.success = new BenchResponse(other.success);
            }
        }

        public doBenchmark_result deepCopy() {
            return new doBenchmark_result(this);
        }

        @Override
        public void clear() {
            this.success = null;
        }

        public BenchResponse getSuccess() {
            return this.success;
        }

        public doBenchmark_result setSuccess(BenchResponse success) {
            this.success = success;
            return this;
        }

        public void unsetSuccess() {
            this.success = null;
        }

        /** Returns true if field success is set (has been assigned a value) and false otherwise */
        public boolean isSetSuccess() {
            return this.success != null;
        }

        public void setSuccessIsSet(boolean value) {
            if (!value) {
                this.success = null;
            }
        }

        public void setFieldValue(_Fields field, Object value) {
            if (field == _Fields.SUCCESS) {
                if (value == null) {
                    unsetSuccess();
                } else {
                    setSuccess((BenchResponse) value);
                }
            }
        }

        public Object getFieldValue(_Fields field) {
            if (field == _Fields.SUCCESS) {
                return getSuccess();
            }
            throw new IllegalStateException();
        }

        /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
        public boolean isSet(_Fields field) {
            if (field == null) {
                throw new IllegalArgumentException();
            }

            if (field == _Fields.SUCCESS) {
                return isSetSuccess();
            }
            throw new IllegalStateException();
        }

        @Override
        public boolean equals(Object that) {
            if (that == null)
                return false;
            if (that instanceof doBenchmark_result)
                return this.equals((doBenchmark_result) that);
            return false;
        }

        public boolean equals(doBenchmark_result that) {
            if (that == null)
                return false;

            boolean this_present_success = this.isSetSuccess();
            boolean that_present_success = that.isSetSuccess();
            if (this_present_success || that_present_success) {
                if (!(this_present_success && that_present_success))
                    return false;
                return this.success.equals(that.success);
            }

            return true;
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public int compareTo(doBenchmark_result other) {
            if (!getClass().equals(other.getClass())) {
                return getClass().getName().compareTo(other.getClass().getName());
            }

            int lastComparison = 0;

            lastComparison = Boolean.compare(isSetSuccess(), other.isSetSuccess());
            if (lastComparison != 0) {
                return lastComparison;
            }
            if (isSetSuccess()) {
                lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
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
            StringBuilder sb = new StringBuilder("doBenchmark_result(");
            boolean first = true;

            sb.append("success:");
            if (this.success == null) {
                sb.append("null");
            } else {
                sb.append(this.success);
            }
            first = false;
            sb.append(")");
            return sb.toString();
        }

        public void validate() throws org.apache.thrift.TException {
            // check for required fields
            // check for sub-struct validity
            if (success != null) {
                success.validate();
            }
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

        private static class doBenchmark_resultStandardSchemeFactory implements SchemeFactory {
            public doBenchmark_resultStandardScheme getScheme() {
                return new doBenchmark_resultStandardScheme();
            }
        }

        private static class doBenchmark_resultStandardScheme extends StandardScheme<doBenchmark_result> {

            public void read(org.apache.thrift.protocol.TProtocol iprot, doBenchmark_result struct) throws org.apache.thrift.TException {
                org.apache.thrift.protocol.TField schemeField;
                iprot.readStructBegin();
                while (true) {
                    schemeField = iprot.readFieldBegin();
                    if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
                        break;
                    }
                    if (schemeField.id == 0) { // SUCCESS
                        if (schemeField.type == TType.STRUCT) {
                            struct.success = new BenchResponse();
                            struct.success.read(iprot);
                            struct.setSuccessIsSet(true);
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

            public void write(org.apache.thrift.protocol.TProtocol oprot, doBenchmark_result struct) throws org.apache.thrift.TException {
                struct.validate();

                oprot.writeStructBegin(STRUCT_DESC);
                if (struct.success != null) {
                    oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
                    struct.success.write(oprot);
                    oprot.writeFieldEnd();
                }
                oprot.writeFieldStop();
                oprot.writeStructEnd();
            }

        }

        private static class doBenchmark_resultTupleSchemeFactory implements SchemeFactory {
            public doBenchmark_resultTupleScheme getScheme() {
                return new doBenchmark_resultTupleScheme();
            }
        }

        private static class doBenchmark_resultTupleScheme extends TupleScheme<doBenchmark_result> {

            @Override
            public void write(org.apache.thrift.protocol.TProtocol prot, doBenchmark_result struct) throws org.apache.thrift.TException {
                TTupleProtocol oprot = (TTupleProtocol) prot;
                BitSet optionals = new BitSet();
                if (struct.isSetSuccess()) {
                    optionals.set(0);
                }
                oprot.writeBitSet(optionals, 1);
                if (struct.isSetSuccess()) {
                    struct.success.write(oprot);
                }
            }

            @Override
            public void read(org.apache.thrift.protocol.TProtocol prot, doBenchmark_result struct) throws org.apache.thrift.TException {
                TTupleProtocol iprot = (TTupleProtocol) prot;
                BitSet incoming = iprot.readBitSet(1);
                if (incoming.get(0)) {
                    struct.success = new BenchResponse();
                    struct.success.read(iprot);
                    struct.setSuccessIsSet(true);
                }
            }
        }

    }

}
