package com.itnovum.Analysis;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;


public class MetadataAnalysisData  extends BaseStepData implements StepDataInterface {

    public RowMetaInterface outputRowMeta;

    public MetadataAnalysisData() {
        super();
    }

}