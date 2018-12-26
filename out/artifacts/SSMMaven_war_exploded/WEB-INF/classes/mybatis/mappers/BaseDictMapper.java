package mybatis.mappers;

import com.HopeRun.SSM.Crm.entity.BaseDict;

public interface BaseDictMapper {
    int deleteByPrimaryKey(String dictId);

    int insert(BaseDict record);

    int insertSelective(BaseDict record);

    BaseDict selectByPrimaryKey(String dictId);

    int updateByPrimaryKeySelective(BaseDict record);

    int updateByPrimaryKey(BaseDict record);
}