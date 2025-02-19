/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ui.controls.resultset.virtual;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.ui.controls.resultset.ResultSetViewer;

public class VirtualEntityEditAction extends Action {
    private ResultSetViewer resultSetViewer;

    public VirtualEntityEditAction(ResultSetViewer resultSetViewer) {
        super("Edit ...");
        this.resultSetViewer = resultSetViewer;
    }

    @Override
    public void run()
    {
        DBSDataContainer dataContainer = resultSetViewer.getDataContainer();
        if (dataContainer == null) {
            return;
        }
        DBSEntity entity = resultSetViewer.getModel().isSingleSource() ? resultSetViewer.getModel().getSingleSource() : null;
        DBVEntity vEntity = resultSetViewer.getModel().getVirtualEntity(entity, true);
        EditVirtualEntityDialog dialog = new EditVirtualEntityDialog(resultSetViewer, entity, vEntity);
        dialog.setInitPage(EditVirtualEntityDialog.InitPage.ATTRIBUTES);
        if (dialog.open() == IDialogConstants.OK_ID) {
            resultSetViewer.refreshMetaData();
        }
    }
}
