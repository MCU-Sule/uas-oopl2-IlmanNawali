package com.example.DAO;

import com.example.Model.FePointEntity;
import com.example.Model.FeTransactionEntity;
import com.example.util.HibernateUtil;
import javafx.collections.FXCollections;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PointDAO implements daoInterface<FePointEntity>{
    @Override
    public int addData(FePointEntity data) {
        return 0;
    }

    @Override
    public int delData(FePointEntity data) {
        return 0;
    }

    @Override
    public int updateData(FePointEntity data) {
        return 0;
    }

    @Override
    public List<FePointEntity> showData() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(FePointEntity.class);

        query.from(FePointEntity.class);

        List<FePointEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }
}
