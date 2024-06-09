package com.maruf.showcase.domain.converters;

import com.maruf.showcase.domain.enumerations.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

  @Override
  public String convertToDatabaseColumn(OrderStatus attribute) {
    return attribute != null ? attribute.name() : null;
  }

  @Override
  public OrderStatus convertToEntityAttribute(String dbData) {
    return dbData != null ? OrderStatus.valueOf(dbData) : null;
  }
}
