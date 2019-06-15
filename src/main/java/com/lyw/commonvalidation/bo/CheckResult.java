package com.lyw.commonvalidation.bo;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class CheckResult {

    private boolean pass;

    private Set<ConstraintViolation<Object>> failInfos;

    public static CheckResult pass() {
        CheckResult result = new CheckResult();
        result.pass = true;
        return result;
    }

    public static CheckResult deny(Set<ConstraintViolation<Object>> failInfos) {
        CheckResult result = new CheckResult();
        result.pass = false;
        result.failInfos = failInfos;
        return result;
    }

    public boolean isPass() {
        return pass;
    }

    public Set<ConstraintViolation<Object>> getFailInfos() {
        return failInfos;
    }

}
