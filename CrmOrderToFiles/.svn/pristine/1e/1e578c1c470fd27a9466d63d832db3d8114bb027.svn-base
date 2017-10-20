package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class initPool {

    private static final Logger _LOG = LoggerFactory.getLogger(initPool.class);
    public static HikariDataSource pool;

    public initPool(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=132.252.3.134)(PORT=1621))(ADDRESS=(PROTOCOL=TCP)(HOST=132.252.3.135)(PORT=1621)))(CONNECT_DATA=(SERVICE_NAME=CSSDB_CSS))))");
        config.setUsername("ZHYX");
        config.setPassword("27rxZm,Tv8VC");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        //config.setConnectionTestQuery("SELECT 1");
        //config.setAutoCommit(true);
        config.setConnectionTimeout(100000);
        config.setMinimumIdle(5); //池中最小空闲链接数量
        config.setMaximumPoolSize(20);//池中最大链接数量
        pool = new HikariDataSource(config);
    }

    public synchronized static HikariDataSource getInstance() {
        if (pool == null ) {
            try {
                if (pool == null) {
                    new initPool();
                }
            } catch (Exception e) {
                _LOG.error(e.getMessage(), e);
            }
        }
        return pool;
    }

    public static String returnChannelId(String channel_nbr){
        String sql = "select T.CHANNEL_ID from EXEC_CHAN_AGENT t where T.BSS_CHANNEL = " + channel_nbr;
        String channel_id = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = initPool.getInstance().getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                channel_id = rs.getString(1);
                //System.out.println(channel_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return channel_id;
    }

    public static void main(String[] args){
//		String a = "101314039809|109010922166|100636018986|379||S1|2017-08-27 22:32:07|99100001075|2017-08-27 22:32:07|12180034||";
//		String[] b = a.split("\\|");
//		System.out.println(b.length);
//		System.out.println(b[9]);
//		String a = "0523";
//		System.out.println(changeCode(a));
        System.out.println("start==="+new Date());
        String channel_nbr = "18084941";
        String a  = initPool.returnChannelId(channel_nbr);
        System.out.println("end==="+new Date());
    }
}