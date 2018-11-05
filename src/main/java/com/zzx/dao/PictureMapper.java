package com.zzx.dao;

import com.zzx.entity.Picture;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    @Select("SELECT COUNT(id) FROM picture WHERE path=#{Path}")
    int select(@Param("Path") String Path);

    @Insert("INSERT INTO picture (path) values (#{Path})")
    int insert(@Param("Path") String Path);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
}