AFreeChart : version 0.0.4

(C) Copyright 2010,2011 by ICOMSYSTECH Co.,Ltd.

The latest version of AFreeChart can be obtained from:
    http://code.google.com/p/afreechart/downloads/list

If you want contact us, please send E-mail or submit comment.
E-mail and comment welcome in Japanese.
  E-mail : icomsystech.oss@gmail.com
  comment page : http://code.google.com/p/afreechart/wiki/CommentPage

===============================================================
1. INTRODUCTION
---------------------------------------------------------------
- AFreeChart is a free charting library for Android(tm) platform. 
- AFreeChart is based on JFreeChart 1.0.13.

  *JFreeChart*
  http://www.jfree.org/jfreechart/

- AFreeChart is licensed under the terms of the GNU Lesser General
  Public Licence (LGPL). 

- Using AFreeChart, you can make graph/chart application very easily.
  There are many Screenshots of DEMO APPLICATION in

  http://afreechart.googlecode.com/svn/doc/screenshot/index.html

  And you can get DEMO APPLICATION from download page.

  http://code.google.com/p/afreechart/downloads/list


===============================================================
2. SYSTEM REQUIREMENTS
---------------------------------------------------------------
Android OS - version 2.1 or later

The operation check is executed as following :
    Nexus One by Google
    Android OS - version 2.2

    GALAXY S by SAMSUNG
    Android OS - version 2.2

===============================================================
3. DOCUMENTATION
---------------------------------------------------------------
API reference is placed at:

    http://code.google.com/p/afreechart/downloads/list


===============================================================
4. DEPENDENCIES
---------------------------------------------------------------
AFreeChart has the following dependencies:

(a) Android 2.1-update 1 (API Level7) or higher.

(b) AFreeGraphics 0.0.3 or later. - To render all shapes, 
    AFreeChart uses functions of AFreeGraphics.
    The runtime jar file is distributed with AFreeChart.

    You can use this library free of charge.
    Copyright owner of AFreeGraphics library is ICOMSYSTECH Co.,Ltd.


===============================================================
5. BUILD
---------------------------------------------------------------
The build environment of AFreeChart.
    Eclipse IDE for Java Developers
    Version: Helios Release


===============================================================
6. LIMITATION
---------------------------------------------------------------
Not all functions are ported from JFreeChart.

We are confirming functions in AFreeChart by executing DEMO APPLICATION.
So, unused class and method by DEMO APPLICATION are not confirmed.

We are planing to implement test using AndroidJUnitTest framework
and/or other more effective way.


===============================================================
7. SUPPORT
---------------------------------------------------------------
Support questions can be posted in the Issues at
    http://code.google.com/p/afreechart/issues/list


===============================================================
8. WHAT's NEW
---------------------------------------------------------------
22 Nov. 2010
  Version 0.0.1 release.
  porting JFreeChart 1.0.13 to Android platform. 

15 Feb. 2011
  Version 0.0.2 release.
  Performance tuning for some functions.
  Fixed bugs and updated documentation.
  Added new JDBC Chart, source location is the following.
    org.afree.chart.demo.activity

26 Oct. 2011
  Version 0.0.3 release.
  Added 6 new sample codes to "afreechart_sample" project.
  Modified the header statement of GNU LGPL, in all source codes.
    Inserted the word "Lesser" before "General".
  Modified "trunk/afreechart/LICENSE" file.
    Changed GNU LGPL text version from "2.1" to "3".

27 Feb. 2012
  Fixed the error about "Cannot find the class file for `*`".
  Added "org.afree.graphics.`*`" files in "afreechart-0.0.3.jar".

24 Apr. 2012
  Added sliding category chart.

===============================================================
9. Trademarks
---------------------------------------------------------------
- Android is a trademark of Google Inc. 
  Use of this trademark is subject to Google Permissions.
- Java is registered trademarks of Oracle and/or its affiliates.
- Other names may be trademarks of their respective owners.


===============================================================
10. Next works
---------------------------------------------------------------
- implement test and confirm ported class more restrictly.
- performance tuning suited to Android framework.
- port more functions from JFreeChart.


===============================================================
11. CONTRIBUTORS
---------------------------------------------------------------
AFreeChart consists by the developer's contribution described 
in the list below:

    - Shiraki Seiyu  (for ICOMSYSTECH Co.,Ltd)
    - Ikeda Koichiro
    - Sato Yoshiaki
    - Niwano Masayoshi
    - Yamakami Souichirou
    - Nada Wataru  (for Tokyo University of information Sciences)
    - Kevin Moray  (Special Thanks)
