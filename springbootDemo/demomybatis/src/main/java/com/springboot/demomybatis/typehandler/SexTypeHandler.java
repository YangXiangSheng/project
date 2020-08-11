package com.springboot.demomybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//声明jdbctype为整型
@MappedJdbcTypes(JdbcType.INTEGER)
//声明javatype为SexEnum
@MappedTypes(value=SexEnum.class)

public class SexTypeHandler extends BaseTypeHandler<SexEnum> {
    //通过列名读取性别
    public SexEnum getNullableResult(ResultSet rs,String col)throws SQLException{
        int sex=rs.getInt(col);
        if(sex !=1&&sex!=2){
            return null;
        }
        return SexEnum.getEnumById(sex);

    }
    //通过下标读取性别
    public SexEnum getNullableResult(ResultSet rs,int idx)throws SQLException{
        int sex=rs.getInt(idx);
        if(sex!=1&&sex!=2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }
    //通过存储过程读取性别
    public SexEnum getNullableResult(CallableStatement cs,int idx)throws SQLException{
        int sex=cs.getInt(idx);
        if(sex!=1&&sex!=2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }
    //设置非空性别参数
    public void setNonNullParameter(PreparedStatement ps,int idx,
                                    SexEnum sex,JdbcType jdbcType)throws SQLException{
        ps.setInt(idx,sex.getId());
    }


}
