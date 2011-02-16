/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * -----------------
 * JDBCXYChartDemoActivity.java
 * -----------------
 * (C) Copyright 2010, by Icom Systech Co., Ltd.
 *
 * Original Author:  Niwano Masayoshi (for Icom Systech Co., Ltd);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 26-Jan-2011 : Version 1.0.0 (NM);
 */ 

package org.afree.chart.demo.activity;

import java.io.File;

import org.afree.chart.demo.view.JDBCXYChartDemoView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * JDBCXYChartDemoActivity
 * 
 * Notice
 * An XY chart that obtains data from a database via JDBC.
 * To run this demo, you need to have a JDBC driver and database that you can access via JDBC,
 * and you must add this activity  name for AndroidManifest.xml
 * We used sqldroid-0.1 for validation.
 * You can get this driver from http://code.google.com/p/sqldroid/
 */
public class JDBCXYChartDemoActivity extends Activity {

    private static String DB_FILE_NAME = "main.sqlite";
    private String dbFilePath;
    
    /**
     * Called when the activity is starting.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbFilePath = this.getApplicationInfo().dataDir + "/" + DB_FILE_NAME;
        JDBCXYChartDemoView mView = new JDBCXYChartDemoView(this, dbFilePath);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(mView);
    }
    
    /**
     * Called when the activity is stopping.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        File f = new File(this.getApplicationInfo().dataDir + "/" + DB_FILE_NAME);
        if (f.exists()) {
            f.delete();
        }
    }
}
