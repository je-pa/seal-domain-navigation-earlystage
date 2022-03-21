package com.cmt.moduleproperty.api;

import java.util.List;
import java.util.Optional;

/**
 * @author JiEun Park
 * @since 1.0
 */
public interface PropertyService {
    /**
     * Property가 이미 존재하면 해당 Property가 VALUE타입이면 값 수정
     *                       해당 Property가 GROUP타입이면 수정 안함
     * , 존재하지 않으면 VALUE타입 생성 후 생성된 Property의 값 수정
     *
     * @param propertyFullName
     * @param value
     * @return 생성되었다면 생성된 객체를, 수정되었다면 수정된 객체를 반환 절대 Null이 올 수 없음음
    */
    Property saveProperty(String propertyFullName, String value);

    /**
     * propertyFullName에 해당하는 Property의 하위자식 찾음
     * @param propertyFullName
     * @return 하위정보 리턴
     * @throws PropertyNotFoundException
     */
    List<Property> findChildProperties(String propertyFullName) throws PropertyNotFoundException;

    /**
     * propertyFullName에 해당하는 Property 찾음
     * @param propertyFullName
     * @return propertyFullName에 해당하는 Optional<Property>
     */
    Optional<Property> findProperty(String propertyFullName);

    /**
     * propertyFullName에 해당하는 value 찾음
     * @param propertyFullName
     * @return propertyFullName에 해당하는 Property가 존재할 때는 값 리턴, 존재하지 않으면 null 리턴
     * @throws PropertyNotFoundException
     */
    String findValue(String propertyFullName) throws PropertyNotFoundException;

    /**
     * VALUE타입 Property를 모두 찾음
     * @return 모든 Value타입 속성값 리턴 없다면 빈 List 리턴
     */
    List<Property> findAllValueTypeProperties();

    /**
     * propertyFullName에 해당하는 Property 삭제
     * @param propertyFullName
     * @return 삭제가 성공했다면 true, 실패했다면 false 리턴
     */
    boolean deleteProperty(String propertyFullName);

}
