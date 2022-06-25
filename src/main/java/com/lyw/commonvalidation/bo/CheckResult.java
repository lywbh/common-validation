package com.lyw.commonvalidation.bo;

import javax.validation.ConstraintViolation;
import java.util.Collection;

public class CheckResult {

    private boolean pass;

    private Collection<ConstraintViolation<?>> failInfos;

    public static CheckResult pass() {
        CheckResult result = new CheckResult();
        result.pass = true;
        return result;
    }

    public static CheckResult deny(Collection<ConstraintViolation<?>> failInfos) {
        CheckResult result = new CheckResult();
        result.pass = false;
        result.failInfos = failInfos;
        return result;
    }

    public boolean isPass() {
        return pass;
    }

    public boolean isDeny() {
        return !pass;
    }

    public Collection<ConstraintViolation<?>> getFailInfos() {
        return failInfos;
    }

}
