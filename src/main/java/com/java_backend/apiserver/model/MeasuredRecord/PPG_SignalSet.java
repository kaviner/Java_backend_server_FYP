package com.java_backend.apiserver.model.MeasuredRecord;

import java.util.List;
import java.util.Objects;
import com.java_backend.apiserver.model.MeasuredRecord.PPG_Signal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown  = true)
public class PPG_SignalSet {

    private List<PPG_Signal> ppgSignalSet;

    public PPG_SignalSet() {
    }

    public PPG_SignalSet(List<PPG_Signal> ppgSignalSet) {
        this.ppgSignalSet = ppgSignalSet;
    }

    public List<PPG_Signal> getPpgSignalSet() {
        return this.ppgSignalSet;
    }

    public void setPpgSignalSet(List<PPG_Signal> ppgSignalSet) {
        this.ppgSignalSet = ppgSignalSet;
    }

    public PPG_SignalSet ppgSignalSet(List<PPG_Signal> ppgSignalSet) {
        setPpgSignalSet(ppgSignalSet);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PPG_SignalSet)) {
            return false;
        }
        PPG_SignalSet pPG_SignalSet = (PPG_SignalSet) o;
        return Objects.equals(ppgSignalSet, pPG_SignalSet.ppgSignalSet);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ppgSignalSet);
    }

    @Override
    public String toString() {
        return "{" +
            " ppgSignalSet='" + getPpgSignalSet() + "'" +
            "}";
    }

    
}
