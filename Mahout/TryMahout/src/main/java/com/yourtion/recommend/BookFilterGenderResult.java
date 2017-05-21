package com.yourtion.recommend;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yourtion
 *         Created at 21/05/2017, TryMahout
 */
public class BookFilterGenderResult {

    private final static int NEIGHBORHOOD_NUM = 2;
    private final static int RECOMMENDER_NUM = 3;

    public static void main(String[] args) throws TasteException, IOException {
        String file = "datafile/book/rating.csv";
        DataModel dataModel = RecommendFactory.buildDataModel(file);
        RecommenderBuilder rb1 = BookEvaluator.userEuclidean(dataModel);
        RecommenderBuilder rb2 = BookEvaluator.itemEuclidean(dataModel);
        RecommenderBuilder rb3 = BookEvaluator.userEuclideanNoPref(dataModel);
        RecommenderBuilder rb4 = BookEvaluator.itemEuclideanNoPref(dataModel);
        System.out.println("");

        System.out.println("User 59: ");
        long uid1 = 59;
        System.out.print("userEuclidean       => ");
        filterGender(uid1, "F", rb1, dataModel);
        System.out.print("itemEuclidean       => ");
        filterGender(uid1, "F", rb2, dataModel);
        System.out.print("userEuclideanNoPref => ");
        filterGender(uid1, "F", rb3, dataModel);
        System.out.print("itemEuclideanNoPref => ");
        filterGender(uid1, "F", rb4, dataModel);
        System.out.println("");

        System.out.println("User 65: ");
        long uid2 = 65;
        System.out.print("userEuclidean       => ");
        filterGender(uid2, "M", rb1, dataModel);
        System.out.print("itemEuclidean       => ");
        filterGender(uid2, "M", rb2, dataModel);
        System.out.print("userEuclideanNoPref => ");
        filterGender(uid2, "M", rb3, dataModel);
        System.out.print("itemEuclideanNoPref => ");
        filterGender(uid2, "M", rb4, dataModel);

    }

    /**
     * 对用户性别进行过滤
     */
    private static void filterGender(long uid, String gender, RecommenderBuilder recommenderBuilder, DataModel dataModel) throws TasteException, IOException {
        Set<Long> userids = getGender("datafile/book/user.csv", gender);


        //计算男性用户打分过的图书
        Set<Long> bookids = new HashSet<Long>();
        for (long uids : userids) {
            LongPrimitiveIterator iter = dataModel.getItemIDsFromUser(uids).iterator();
            while (iter.hasNext()) {
                long bookid = iter.next();
                bookids.add(bookid);
            }
        }

        IDRescorer rescorer = new FilterRescorer(bookids);
        List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM, rescorer);
        RecommendFactory.showItems(uid, list, false);
    }

    /**
     * 获得特定性别用户ID
     */
    private static Set<Long> getGender(String file, String gender) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(file)));
        Set<Long> userids = new HashSet<Long>();
        String s = null;
        while ((s = br.readLine()) != null) {
            String[] cols = s.split(",");
            if (cols[1].equals(gender)) {// 判断性别
                userids.add(Long.parseLong(cols[0]));
            }
        }
        br.close();
        return userids;
    }
}

/**
 * 对结果重计算
 */
class FilterRescorer implements IDRescorer {
    final private Set<Long> userids;

    FilterRescorer(Set<Long> userids) {
        this.userids = userids;
    }

    public double rescore(long id, double originalScore) {
        return isFiltered(id) ? Double.NaN : originalScore;
    }

    public boolean isFiltered(long id) {
        return userids.contains(id);
    }
}
