package com.zj.blog.service.impl;

import com.zj.blog.dao.TypeDaoMapper;
import com.zj.blog.pojo.BlogTypes;
import com.zj.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TypeServiceImpl
 * @Description TODO
 * @Author 张杰
 * @Time 2020/11/27/15:55
 * @Version 1.0
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDaoMapper typeDaoMapper;

    @Transactional
    @Override
    public int saveType(BlogTypes types) {
        return  typeDaoMapper.insertTypes(types);
    }

    @Transactional
    @Override
    public BlogTypes getType(Long id) {
        return typeDaoMapper.selectTypes(id);
    }

    @Transactional
    @Override
    public List<BlogTypes> listType() {
        return typeDaoMapper.selectAllTypes();
    }

    @Transactional
    @Override
    public int updateType(BlogTypes types) {
        return typeDaoMapper.updateTypes(types);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDaoMapper.deleteTypes(id);
    }

    @Transactional
    @Override
    public BlogTypes getTypeByName(String typeName) {
        return typeDaoMapper.selectTypeByName(typeName);
    }

    @Override
    public List<BlogTypes> listAllTypeAndBlog() {
        return typeDaoMapper.selectAllTypeAndBlog();
    }

}
