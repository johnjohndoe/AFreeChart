/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * -------------
 * Timeline.java
 * -------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  Bill Kelemen;
 * Contributor(s):   David Gilbert (for Object Refinery Limited);
 *
 * Changes
 * -------
 * 23-May-2003 : Version 1 (BK);
 * 09-Sep-2003 : Changed some method and parameter names (DG);
 * 02-Feb-2007 : Removed author tags all over JFreeChart sources (DG);
 *
 */

package org.afree.chart.axis;


import java.util.Date;

/**
 * An interface that defines the contract for a Timeline.
 * <P>
 * A Timeline will present a series of values to be used for an axis. Each
 * Timeline must provide transformation methods between domain values and
 * timeline values. In theory many transformations are possible. This interface
 * has been implemented completely in
 * {@link org.afree.chart.axis.SegmentedTimeline}.
 * <P>
 * A timeline can be used as parameter to a
 * {@link org.afree.chart.axis.DateAxis} to define the values that this axis
 * supports. As an example, the {@link org.afree.chart.axis.SegmentedTimeline}
 * implements a timeline formed by segments of equal length (ex. days, hours,
 * minutes) where some segments can be included in the timeline and others
 * excluded. Therefore timelines like "working days" or "working hours" can be
 * created where non-working days or non-working hours respectively can be
 * removed from the timeline, and therefore from the axis. This creates a smooth
 * plot with equal separation between all included segments.
 * <P>
 * Because Timelines were created mainly for Date related axis, values are
 * represented as longs instead of doubles. In this case, the domain value is
 * just the number of milliseconds since January 1, 1970, 00:00:00 GMT as
 * defined by the getTime() method of {@link java.util.Date}.
 *
 * @see org.afree.chart.axis.SegmentedTimeline
 * @see org.afree.chart.axis.DateAxis
 */
public interface Timeline {

    /**
     * Translates a millisecond (as defined by java.util.Date) into an index
     * along this timeline.
     *
     * @param millisecond  the millisecond.
     *
     * @return A timeline value.
     */
    long toTimelineValue(long millisecond);

    /**
     * Translates a date into a value on this timeline.
     *
     * @param date  the date.
     *
     * @return A timeline value
     */
    long toTimelineValue(Date date);

    /**
     * Translates a value relative to this timeline into a domain value. The
     * domain value obtained by this method is not always the same domain value
     * that could have been supplied to
     * translateDomainValueToTimelineValue(domainValue).
     * This is because the original tranformation may not be complete
     * reversable.
     *
     * @see org.afree.chart.axis.SegmentedTimeline
     *
     * @param timelineValue  a timeline value.
     *
     * @return A domain value.
     */
    long toMillisecond(long timelineValue);

    /**
     * Returns <code>true</code> if a value is contained in the timeline values.
     *
     * @param millisecond  the millisecond.
     *
     * @return <code>true</code> if value is contained in the timeline and
     *         <code>false</code> otherwise.
     */
    boolean containsDomainValue(long millisecond);

    /**
     * Returns <code>true</code> if a date is contained in the timeline values.
     *
     * @param date  the date to verify.
     *
     * @return <code>true</code> if value is contained in the timeline and
     *         <code>false</code>  otherwise.
     */
    boolean containsDomainValue(Date date);

    /**
     * Returns <code>true</code> if a range of values are contained in the
     * timeline.
     *
     * @param fromMillisecond  the start of the range to verify.
     * @param toMillisecond  the end of the range to verify.
     *
     * @return <code>true</code> if the range is contained in the timeline or
     *         <code>false</code> otherwise
     */
    boolean containsDomainRange(long fromMillisecond, long toMillisecond);

    /**
     * Returns <code>true</code> if a range of dates are contained in the
     * timeline.
     *
     * @param fromDate  the start of the range to verify.
     * @param toDate  the end of the range to verify.
     *
     * @return <code>true</code> if the range is contained in the timeline or
     *         <code>false</code> otherwise
     */
    boolean containsDomainRange(Date fromDate, Date toDate);

}
