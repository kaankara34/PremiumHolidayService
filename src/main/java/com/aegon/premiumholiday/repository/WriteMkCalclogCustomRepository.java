package com.aegon.premiumholiday.repository;

import java.util.Date;

public interface WriteMkCalclogCustomRepository {
    void writeMkCalclog(Long pseq, Long ppolid, String puser, String plogtype, Date pcalcdate, String pcalcstatus, String donemselmkLocal,
                        String lokalmkLocal, String donemselmkIfrs, String lokalmkIfrs);
}
