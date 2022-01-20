package com.example.DAO;

import com.example.Model.FeMemberEntity;
import com.example.Model.FeTransactionEntity;
import com.example.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TransactionDAO implements daoInterface<FeTransactionEntity>{
    @Override
    public int addData(FeTransactionEntity data) {
        Session s = HibernateUtil.getSession();
        Transaction t = s.beginTransaction();
        s.save(data);
        t.commit();
        s.close();
        return 0;
    }

    @Override
    public int delData(FeTransactionEntity data) {
        return 0;
    }

    @Override
    public int updateData(FeTransactionEntity data) {
        return 0;
    }

    @Override
    public List<FeTransactionEntity> showData() {
        Session s = HibernateUtil.getSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(FeTransactionEntity.class);

        query.from(FeTransactionEntity.class);

        List<FeTransactionEntity> clist = s.createQuery(query).getResultList();
        s.close();

        return FXCollections.observableArrayList(clist);
    }

}
