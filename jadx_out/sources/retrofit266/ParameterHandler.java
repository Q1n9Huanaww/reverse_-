package retrofit266;

import com.lidroid.xutils.http.client.multipart.MIME;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp366.MultipartBody;
import okhttp366.RequestBody;

/* JADX INFO: loaded from: classes2.dex */
abstract class ParameterHandler<T> {
    abstract void apply(RequestBuilder requestBuilder, @Nullable T t) throws IOException;

    ParameterHandler() {
    }

    final ParameterHandler<Iterable<T>> iterable() {
        return new ParameterHandler<Iterable<T>>() { // from class: retrofit266.ParameterHandler.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // retrofit266.ParameterHandler
            public void apply(RequestBuilder builder, @Nullable Iterable<T> values) throws IOException {
                if (values == null) {
                    return;
                }
                for (T value : values) {
                    ParameterHandler.this.apply(builder, value);
                }
            }
        };
    }

    final ParameterHandler<Object> array() {
        return new ParameterHandler<Object>() { // from class: retrofit266.ParameterHandler.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // retrofit266.ParameterHandler
            void apply(RequestBuilder builder, @Nullable Object values) throws IOException {
                if (values == null) {
                    return;
                }
                int size = Array.getLength(values);
                for (int i = 0; i < size; i++) {
                    ParameterHandler.this.apply(builder, Array.get(values, i));
                }
            }
        };
    }

    static final class RelativeUrl extends ParameterHandler<Object> {
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5092p;

        RelativeUrl(Method method, int p) {
            this.method = method;
            this.f5092p = p;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable Object value) {
            if (value == null) {
                throw Utils.parameterError(this.method, this.f5092p, "@Url parameter is null.", new Object[0]);
            }
            builder.setRelativeUrl(value);
        }
    }

    static final class Header<T> extends ParameterHandler<T> {
        private final boolean allowUnsafeNonAsciiValues;
        private final String name;
        private final Converter<T, String> valueConverter;

        Header(String name, Converter<T, String> valueConverter, boolean allowUnsafeNonAsciiValues) {
            this.name = (String) Objects.requireNonNull(name, "name == null");
            this.valueConverter = valueConverter;
            this.allowUnsafeNonAsciiValues = allowUnsafeNonAsciiValues;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) throws IOException {
            String headerValue;
            if (value == null || (headerValue = this.valueConverter.convert(value)) == null) {
                return;
            }
            builder.addHeader(this.name, headerValue, this.allowUnsafeNonAsciiValues);
        }
    }

    static final class Path<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Method method;
        private final String name;

        /* JADX INFO: renamed from: p */
        private final int f5090p;
        private final Converter<T, String> valueConverter;

        Path(Method method, int p, String name, Converter<T, String> valueConverter, boolean encoded) {
            this.method = method;
            this.f5090p = p;
            this.name = (String) Objects.requireNonNull(name, "name == null");
            this.valueConverter = valueConverter;
            this.encoded = encoded;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) throws IOException {
            if (value == null) {
                throw Utils.parameterError(this.method, this.f5090p, "Path parameter \"" + this.name + "\" value must not be null.", new Object[0]);
            }
            builder.addPathParam(this.name, this.valueConverter.convert(value), this.encoded);
        }
    }

    static final class Query<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Query(String name, Converter<T, String> valueConverter, boolean encoded) {
            this.name = (String) Objects.requireNonNull(name, "name == null");
            this.valueConverter = valueConverter;
            this.encoded = encoded;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) throws IOException {
            String queryValue;
            if (value == null || (queryValue = this.valueConverter.convert(value)) == null) {
                return;
            }
            builder.addQueryParam(this.name, queryValue, this.encoded);
        }
    }

    static final class QueryName<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Converter<T, String> nameConverter;

        QueryName(Converter<T, String> nameConverter, boolean encoded) {
            this.nameConverter = nameConverter;
            this.encoded = encoded;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) throws IOException {
            if (value == null) {
                return;
            }
            builder.addQueryParam(this.nameConverter.convert(value), null, this.encoded);
        }
    }

    static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5091p;
        private final Converter<T, String> valueConverter;

        QueryMap(Method method, int p, Converter<T, String> valueConverter, boolean encoded) {
            this.method = method;
            this.f5091p = p;
            this.valueConverter = valueConverter;
            this.encoded = encoded;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit266.ParameterHandler
        public void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
            if (value == null) {
                throw Utils.parameterError(this.method, this.f5091p, "Query map was null", new Object[0]);
            }
            for (Map.Entry<String, T> entry : value.entrySet()) {
                String entryKey = entry.getKey();
                if (entryKey == null) {
                    throw Utils.parameterError(this.method, this.f5091p, "Query map contained null key.", new Object[0]);
                }
                T entryValue = entry.getValue();
                if (entryValue == null) {
                    throw Utils.parameterError(this.method, this.f5091p, "Query map contained null value for key '" + entryKey + "'.", new Object[0]);
                }
                String convertedEntryValue = this.valueConverter.convert(entryValue);
                if (convertedEntryValue == null) {
                    throw Utils.parameterError(this.method, this.f5091p, "Query map value '" + entryValue + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + entryKey + "'.", new Object[0]);
                }
                builder.addQueryParam(entryKey, convertedEntryValue, this.encoded);
            }
        }
    }

    static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean allowUnsafeNonAsciiValues;
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5086p;
        private final Converter<T, String> valueConverter;

        HeaderMap(Method method, int p, Converter<T, String> valueConverter, boolean allowUnsafeNonAsciiValues) {
            this.method = method;
            this.f5086p = p;
            this.valueConverter = valueConverter;
            this.allowUnsafeNonAsciiValues = allowUnsafeNonAsciiValues;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit266.ParameterHandler
        public void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
            if (value == null) {
                throw Utils.parameterError(this.method, this.f5086p, "Header map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : value.entrySet()) {
                String headerName = entry.getKey();
                if (headerName == null) {
                    throw Utils.parameterError(this.method, this.f5086p, "Header map contained null key.", new Object[0]);
                }
                T headerValue = entry.getValue();
                if (headerValue == null) {
                    throw Utils.parameterError(this.method, this.f5086p, "Header map contained null value for key '" + headerName + "'.", new Object[0]);
                }
                builder.addHeader(headerName, this.valueConverter.convert(headerValue), this.allowUnsafeNonAsciiValues);
            }
        }
    }

    static final class Headers extends ParameterHandler<okhttp366.Headers> {
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5087p;

        Headers(Method method, int p) {
            this.method = method;
            this.f5087p = p;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit266.ParameterHandler
        public void apply(RequestBuilder builder, @Nullable okhttp366.Headers headers) {
            if (headers == null) {
                throw Utils.parameterError(this.method, this.f5087p, "Headers parameter must not be null.", new Object[0]);
            }
            builder.addHeaders(headers);
        }
    }

    static final class Field<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        Field(String name, Converter<T, String> valueConverter, boolean encoded) {
            this.name = (String) Objects.requireNonNull(name, "name == null");
            this.valueConverter = valueConverter;
            this.encoded = encoded;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) throws IOException {
            String fieldValue;
            if (value == null || (fieldValue = this.valueConverter.convert(value)) == null) {
                return;
            }
            builder.addFormField(this.name, fieldValue, this.encoded);
        }
    }

    static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5085p;
        private final Converter<T, String> valueConverter;

        FieldMap(Method method, int p, Converter<T, String> valueConverter, boolean encoded) {
            this.method = method;
            this.f5085p = p;
            this.valueConverter = valueConverter;
            this.encoded = encoded;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit266.ParameterHandler
        public void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
            if (value == null) {
                throw Utils.parameterError(this.method, this.f5085p, "Field map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : value.entrySet()) {
                String entryKey = entry.getKey();
                if (entryKey == null) {
                    throw Utils.parameterError(this.method, this.f5085p, "Field map contained null key.", new Object[0]);
                }
                T entryValue = entry.getValue();
                if (entryValue == null) {
                    throw Utils.parameterError(this.method, this.f5085p, "Field map contained null value for key '" + entryKey + "'.", new Object[0]);
                }
                String fieldEntry = this.valueConverter.convert(entryValue);
                if (fieldEntry == null) {
                    throw Utils.parameterError(this.method, this.f5085p, "Field map value '" + entryValue + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + entryKey + "'.", new Object[0]);
                }
                builder.addFormField(entryKey, fieldEntry, this.encoded);
            }
        }
    }

    static final class Part<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final okhttp366.Headers headers;
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5088p;

        Part(Method method, int p, okhttp366.Headers headers, Converter<T, RequestBody> converter) {
            this.method = method;
            this.f5088p = p;
            this.headers = headers;
            this.converter = converter;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) {
            if (value == null) {
                return;
            }
            try {
                RequestBody body = this.converter.convert(value);
                builder.addPart(this.headers, body);
            } catch (IOException e) {
                throw Utils.parameterError(this.method, this.f5088p, "Unable to convert " + value + " to RequestBody", e);
            }
        }
    }

    static final class RawPart extends ParameterHandler<MultipartBody.Part> {
        static final RawPart INSTANCE = new RawPart();

        private RawPart() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit266.ParameterHandler
        public void apply(RequestBuilder builder, @Nullable MultipartBody.Part value) {
            if (value != null) {
                builder.addPart(value);
            }
        }
    }

    static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5089p;
        private final String transferEncoding;
        private final Converter<T, RequestBody> valueConverter;

        PartMap(Method method, int p, Converter<T, RequestBody> valueConverter, String transferEncoding) {
            this.method = method;
            this.f5089p = p;
            this.valueConverter = valueConverter;
            this.transferEncoding = transferEncoding;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit266.ParameterHandler
        public void apply(RequestBuilder builder, @Nullable Map<String, T> value) throws IOException {
            if (value == null) {
                throw Utils.parameterError(this.method, this.f5089p, "Part map was null.", new Object[0]);
            }
            for (Map.Entry<String, T> entry : value.entrySet()) {
                String entryKey = entry.getKey();
                if (entryKey == null) {
                    throw Utils.parameterError(this.method, this.f5089p, "Part map contained null key.", new Object[0]);
                }
                T entryValue = entry.getValue();
                if (entryValue == null) {
                    throw Utils.parameterError(this.method, this.f5089p, "Part map contained null value for key '" + entryKey + "'.", new Object[0]);
                }
                okhttp366.Headers headers = okhttp366.Headers.m5098of("Content-Disposition", "form-data; name=\"" + entryKey + "\"", MIME.CONTENT_TRANSFER_ENC, this.transferEncoding);
                builder.addPart(headers, this.valueConverter.convert(entryValue));
            }
        }
    }

    static final class Body<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final Method method;

        /* JADX INFO: renamed from: p */
        private final int f5084p;

        Body(Method method, int p, Converter<T, RequestBody> converter) {
            this.method = method;
            this.f5084p = p;
            this.converter = converter;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) {
            if (value == null) {
                throw Utils.parameterError(this.method, this.f5084p, "Body parameter value must not be null.", new Object[0]);
            }
            try {
                RequestBody body = this.converter.convert(value);
                builder.setBody(body);
            } catch (IOException e) {
                throw Utils.parameterError(this.method, e, this.f5084p, "Unable to convert " + value + " to RequestBody", new Object[0]);
            }
        }
    }

    static final class Tag<T> extends ParameterHandler<T> {
        final Class<T> cls;

        Tag(Class<T> cls) {
            this.cls = cls;
        }

        @Override // retrofit266.ParameterHandler
        void apply(RequestBuilder builder, @Nullable T value) {
            builder.addTag(this.cls, value);
        }
    }
}
