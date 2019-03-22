package com.funfaire.infra.deserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public abstract class AbstractDeserializer<T> extends JsonDeserializer<T> {

    public abstract T deserialize(final JsonNode jsonNode);

    @Override
    public T deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        final ObjectCodec codec = jsonParser.getCodec();
        final JsonNode jsonNode = (JsonNode) codec.readTree(jsonParser);
        return deserialize(jsonNode);
    }

    protected String getFieldTextValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).textValue() : null;
    }

    protected Double getFieldDoubleValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).doubleValue() : null;
    }

    protected Date getFieldDateValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? new Date(node.get(fieldName.toString()).asLong()) : null;
    }

    protected LocalDateTime getFieldLocalDateTime(final JsonNode node, final Enum<?> fieldName, final DateTimeFormatter formatter) {
    	return this.hasNonNullAndNonEmpty(node, fieldName) ? LocalDateTime.parse(node.get(fieldName.toString()).textValue(), formatter) : null;
    }
    
    
    protected Long getFieldLongValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).asLong() : null;
    }

    protected Integer getFieldIntegerValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).asInt() : null;
    }

    protected boolean getFieldBooleanValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? node.get(fieldName.toString()).asBoolean() : false;
    }

    protected BigDecimal getFieldBigDecimalValue(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? new BigDecimal(node.get(fieldName.toString()).asText()) : null;
    }

    protected BigDecimal getFieldBigDecimalValue(final JsonNode node, final Enum<?> fieldName, final int scale, final RoundingMode roundingMode) {
        return this.hasNonNullAndNonEmpty(node, fieldName) ? (new BigDecimal(node.get(fieldName.toString()).asText())).setScale(scale, roundingMode) : null;
    }

    protected boolean hasNonNullAndNonEmpty(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNull(node, fieldName) && node.get(fieldName.toString()).asText().length() > 0;
    }

    protected boolean hasNonNull(final JsonNode node, final Enum<?> fieldName) {
        return this.has(node, fieldName) && node.hasNonNull(fieldName.toString());
    }

    protected boolean has(final JsonNode node, final Enum<?> fieldName) {
        return node.has(fieldName.toString());
    }

    protected JsonNode get(final JsonNode node, final Enum<?> fieldName) {
        return node.get(fieldName.toString());
    }

    protected boolean isArray(final JsonNode node, final Enum<?> fieldName) {
        return this.hasNonNull(node, fieldName) && this.get(node, fieldName).isArray();
    }

    protected boolean isEmptyArray(final JsonNode node, final Enum<?> fieldName) {
        return this.isArray(node, fieldName) && this.get(node, fieldName).size() == 0;
    }

    protected JsonNode getArrayNodeValue(final JsonNode jsonNodeParam, final Enum<?> fieldName) {
        final JsonNode jsonNode = this.hasNonNull(jsonNodeParam, fieldName) ? jsonNodeParam.get(fieldName.toString()) : null;
        return jsonNode instanceof ArrayNode ? jsonNode : (new ArrayNode(JsonNodeFactory.instance)).add(jsonNode);
    }

}
