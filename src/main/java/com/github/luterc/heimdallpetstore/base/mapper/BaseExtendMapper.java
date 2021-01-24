
package com.github.luterc.heimdallpetstore.base.mapper;

import java.util.List;

/**
 * mapStruct接口定义
 *
 * @param <D> DTO类
 * @param <E> Entity实体类
 * @param <V> VO类
 * @author Luter
 */
public interface BaseExtendMapper<D, E, V> {
    /////vo 与entity

    /**
     * VO转Entity
     *
     * @param vo vo
     * @return 实体类 e
     */
    E voToEntity(V vo);

    /**
     * Entity转VO
     *
     * @param entity the entity
     * @return the d
     */
    V entityToVO(E entity);

    /**
     * VO集合转Entity集合
     *
     * @param voList the dto list
     * @return the list
     */
    List<E> voListToEntityList(List<V> voList);

    /**
     * Entity集合转VO集合
     *
     * @param entityList 实体类集合
     * @return the list VO集合
     */
    List<V> entityListToVOList(List<E> entityList);

    /**
     * Entity list to dto list list.
     *
     * @param entityList the entity list
     * @return the list
     */
    List<D> entityListToDTOList(List<E> entityList);

    /////vo 与 dto

    /**
     * VO转DTO
     *
     * @param vo vo
     * @return 实体类 d
     */
    D voToDTO(V vo);

    /**
     * DTO转VO
     *
     * @param dto DTO
     * @return VO v
     */
    V dtoToVO(D dto);

    /**
     * VO集合转DTO集合
     *
     * @param voList the dto list
     * @return the list
     */
    List<D> voListToDTOList(List<V> voList);

    /**
     * DTO集合转VO集合
     *
     * @param dtoList DTO集合
     * @return the list VO集合
     */
    List<V> dtoListToVOList(List<D> dtoList);

    /////dto 与实体类

    /**
     * DTO转Entity
     *
     * @param dto the dto
     * @return the e
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     *
     * @param entity the entity
     * @return the d
     */
    D toDto(E entity);

    /**
     * DTO集合转Entity集合
     *
     * @param dtoList the dto list
     * @return the list
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     *
     * @param entityList the entity list
     * @return the list
     */
    List<D> toDto(List<E> entityList);
}
