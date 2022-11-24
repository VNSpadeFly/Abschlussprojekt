package com.itnovum.Analysis;

import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.row.RowDataUtil;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.*;


public class MetadataAnalysisStep extends BaseStep implements StepInterface {

    private MetadataAnalysisData data;
    private MetadataAnalysisMeta meta;

    public MetadataAnalysisStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int copyNr, TransMeta transMeta,
                            Trans trans) {
        super(stepMeta, stepDataInterface, copyNr, transMeta, trans);
    }

    public boolean init(StepMetaInterface smi, StepDataInterface sdi) {
        meta = (MetadataAnalysisMeta) smi;
        data = (MetadataAnalysisData) sdi;

        return super.init(smi, sdi);
    }

    public boolean processRow(StepMetaInterface smi, StepDataInterface sdi) throws KettleException {
        meta = (MetadataAnalysisMeta) smi;
        data = (MetadataAnalysisData) sdi;

        Object[] inputRow = getRow(); // get row, blocks when needed!
        if (inputRow == null) // no more input to be expected…
        {
            setOutputDone();
            return false;
        }

        if (first) {
            first = false;
            data.outputRowMeta = (RowMetaInterface) getInputRowMeta().clone();
            meta.getFields(data.outputRowMeta, getStepname(), null, null, this);
        }

        Object[] Object = RowDataUtil.addRowData(inputRow.clone(), getInputRowMeta().size(),
                new Object[] { meta.getNewField() });
        Object[] newRow = RowDataUtil.createResizedCopy(Object, data.outputRowMeta.size());

        putRow(data.outputRowMeta, newRow);

        return true;
    }

    public void dispose(StepMetaInterface smi, StepDataInterface sdi) {
        meta = (MetadataAnalysisMeta) smi;
        data = (MetadataAnalysisData) sdi;

        super.dispose(smi, sdi);
    }

}
