package  itcr.laborm.dbhelpers;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;

public class DatabaseConfigUtil extends OrmLiteConfigUtil{

    public static void main(String[] args) throws SQLException, IOException{
        writeConfigFile("ormlite_config.txt");
    }

}
