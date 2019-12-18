package com.teamcenter.rac.cdeasia.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.schemas.soa._2006_03.exceptions.ServiceException;
import com.teamcenter.services.rac.core.DataManagementService;
import com.teamcenter.services.rac.core._2008_06.DataManagement;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateIn;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateInput;
import com.teamcenter.services.rac.core._2008_06.DataManagement.CreateResponse;

public class CDEFabricationHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	Text itemIDText;
	Text itemNameText;
	Text revIDText;
	Text itemDescText;
	
	TCSession session;
	
	private TCComponentItem item;
	private TCComponentItemRevision itemRev;
	
	public CDEFabricationHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AbstractAIFUIApplication application = AIFUtility.getCurrentApplication();
		TCSession session = (TCSession) application.getSession();
		UI();
		return null;
	}

	void UI()
	{
		
		final CreateIn itemDef =new CreateIn();
		final CreateInput itemRevisionDef = new CreateInput();


	
		// TODO Auto-generated method stub
		//Display display = new Display ();
		final Shell shell=new Shell();
		shell.setSize(245, 200);
		shell.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite.heightHint = 118;
		composite.setLayoutData(gd_composite);
		
		Label ItemIdLabel = new Label(composite, SWT.NONE);
		ItemIdLabel.setText("Item ID");
		new Label(composite, SWT.NONE);
		
		itemIDText = new Text(composite, SWT.BORDER);
		itemIDText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label ItemNameLabel = new Label(composite, SWT.NONE);
		ItemNameLabel.setText("Item Name");
		new Label(composite, SWT.NONE);
		
		itemNameText = new Text(composite, SWT.BORDER);
		itemNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label RevisionIdLabel = new Label(composite, SWT.NONE);
		RevisionIdLabel.setText("Revision ID");
		new Label(composite, SWT.NONE);
		
		revIDText = new Text(composite, SWT.BORDER);
		revIDText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label itemDescLabel = new Label(composite, SWT.NONE);
		itemDescLabel.setText("Item Description");
		new Label(composite, SWT.NONE);
		
		itemDescText = new Text(composite, SWT.BORDER);
		itemDescText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new GridLayout(7, false));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_composite_1.heightHint = 40;
		composite_1.setLayoutData(gd_composite_1);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button btnOk = new Button(composite_1, SWT.NONE);
		GridData gd_btnOk = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnOk.widthHint = 50;
		btnOk.setLayoutData(gd_btnOk);
		btnOk.setText("OK");
		
		
		btnOk.addSelectionListener(new SelectionListener() {

			private Object dmService;

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
				AbstractAIFUIApplication application = AIFUtility.getCurrentApplication();
				TCSession session = (TCSession) application.getSession();
				dmService = DataManagementService.getService(session);
				
					//	TCComponentFolder folder= session.getUser().getHomeFolder();
						//TCComponentItemType itype=(TCComponentItemType) session.getTypeComponent("CD5CDEFabCAD");
						 CreateIn itemDef = new CreateIn(); 
						 itemDef.data.boName = "CD5CDEFab"; 
						
						 
						 itemDef.data.stringProps.put("item_id",itemIDText.getText()); 
						 itemDef.data.stringProps.put("object_name",itemNameText.getText()); 
						 itemDef.data.stringProps.put("object_desc",itemDescText.getText());
						 
						 CreateInput itemRevisionDef = new CreateInput();
						 itemRevisionDef.boName = "CD5CDEFabRevision"; 
						 itemRevisionDef.stringProps.put("object_desc",itemDescText.getText()); 
					//	 itemRevisionDef.stringProps.put("object_name","ASD");


						 itemDef.data.compoundCreateInput.put("revision",new CreateInput[]{itemRevisionDef});
						 try 
						 { 
						
							 CreateResponse createObjResponse =  ((DataManagement) dmService).createObjects(new CreateIn[]{itemDef});
							 
						 } 
						 catch (ServiceException e) 
						 { 
							 e.printStackTrace(); 
						 }
				 
			 } 		
			
			
		});
		
		Button btnCancel = new Button(composite_1, SWT.NONE);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		shell.pack();
		shell.open();
		
	}
	
	
}
