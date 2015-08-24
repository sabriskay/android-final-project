package com.mybaby.android_final_project.test;

import android.content.Context;
import android.widget.Toast;

import com.mybaby.android_final_project.ezgrowth.bmiForAge;
import com.mybaby.android_final_project.ezgrowth.headCircumferenceForAge;
import com.mybaby.android_final_project.ezgrowth.lengthForAgeInfant;
import com.mybaby.android_final_project.ezgrowth.statureForAge;
import com.mybaby.android_final_project.ezgrowth.weightForAge;
import com.mybaby.android_final_project.ezgrowth.weightForAgeInfant;
import com.mybaby.android_final_project.ezgrowth.weightForLengthInfant;
import com.mybaby.android_final_project.ezgrowth.weightForStature;
import com.mybaby.android_final_project.ezgrowth.zTable;

public class EzGrowth
{
    /** Called when the activity is first created. */
   /* public void onCreate()
    {
      *//*  super.onCreate(icicle);
        setContentView(R.layout.percentile);*//*
        
        // Implement WeightForAgeBtn click
        Button l_oResultsBtn = (Button) findViewById(R.id.ResultsBtn);
        l_oResultsBtn.setOnClickListener(new Button.OnClickListener() 
        {             
        	public void onClick(View v) 
        	{
        		Double l_dBMI = -0.1;
        		Double l_dHeadCircumference = -0.1;
        		Double l_dLength = -0.1;
        		Double l_dStature = -0.1;
        		Double l_dSex = -0.1;
        		Double l_dAge = -0.1;
        		Double l_dWeight = -0.1;*/
        		
                // Set sex to male or female according to marked radio button
/*                RadioButton l_oRadioButton = (RadioButton) findViewById(R.id.MaleRadioButton);
                if (l_oRadioButton.isChecked())
                {
                	// Set sex to male
                	l_dSex = 1.0;
                }
                else
                {
                	// Set sex to female
                	l_dSex = 2.0;
                }
                               
                try
                {
                	EditText l_oStature = (EditText) findViewById(R.id.StatureText);
                	if (! l_oStature.getText().toString().equalsIgnoreCase(""))
                		l_dStature = Double.parseDouble(l_oStature.getText().toString());
                }
                catch(Exception e)
                {
                	showAlert("Incorrect Value", 0, "Incorrect value inserted in Stature.", "Okay", true);
                	return;
                }
                
                try
                {
                	EditText l_oHeadSize = (EditText) findViewById(R.id.HeadSizeText);
                	if (! l_oHeadSize.getText().toString().equalsIgnoreCase(""))
                		l_dHeadCircumference = Double.parseDouble(l_oHeadSize.getText().toString());
                }
                catch(Exception e)
                {

                	showAlert("Incorrect Value",0,"Incorrect value inserted in Head Circumference.","Okay",true);
                	return;
                }
                                
                try
                {
                	EditText l_oLength = (EditText) findViewById(R.id.LengthText);
                	if (! l_oLength.getText().toString().equalsIgnoreCase(""))
                		l_dLength = Double.parseDouble(l_oLength.getText().toString());
                }
                catch(Exception e)
                {
                	showAlert("Incorrect Value",0,"Incorrect value inserted in Length.","Okay",true);
                	return;
                }
                
                try
                {
                	EditText l_oWeight = (EditText) findViewById(R.id.WeightText);
                	if (! l_oWeight.getText().toString().equalsIgnoreCase(""))
                		l_dWeight = Double.parseDouble(l_oWeight.getText().toString());
                }
                catch(Exception e)
                {
                	showAlert("Incorrect Value",0,"Incorrect value inserted in Weight.","Okay",true);
                	return;
                }
                
                try
                {
                	EditText l_oAge = (EditText) findViewById(R.id.AgeText);
                	if (! l_oAge.getText().toString().equalsIgnoreCase(""))
                		l_dAge = Double.parseDouble(l_oAge.getText().toString());
                }
                catch(Exception e)
                {
                	showAlert("Incorrect Value",0,"Incorrect value inserted in Age.","Okay",true);
                	return;
                }*/
                
//                String l_sResults[] = {""};
//        		if ((l_dWeight >= 0.0) && (l_dStature >= 24.0) && (l_dAge >= 24.0) && (l_dAge <= 240.5))
//        		{
//        			l_dBMI = l_dWeight/((l_dStature * l_dStature) / 10000);
//        			UpdateBMIForAge(l_dBMI,l_dAge,l_dSex,l_sResults);
//        		}
//        		if ((l_dHeadCircumference >= 0.0) && (l_dAge >= 0.0) && (l_dAge <= 36.0))
//        		{
//        			UpdateHeadForAge(l_dHeadCircumference,l_dAge,l_dSex,l_sResults);
//        		}
//        		if ((l_dAge >= 0.0) && (l_dAge <= 35.5) && (l_dLength >= 0.0))
//        		{
//        			UpdateLengthForAge(l_dLength,l_dAge,l_dSex,l_sResults);
//        		}
//        		if ((l_dAge >= 24.0) && (l_dAge <= 240.0) && (l_dStature >= 0.0))
//        		{
//        			UpdateStatureForAge(l_dStature,l_dAge,l_dSex,l_sResults);
//        		}
//        		if ((l_dAge >= 36.0) && (l_dAge <= 240.0) && (l_dWeight >= 0.0))
//        		{
//        			UpdateWeightForAge(l_dWeight,l_dAge,l_dSex,l_sResults);
//        		}
//        		if ((l_dAge < 36.0) && (l_dAge >= 0.0) && (l_dWeight >= 0.0))
//        		{
//        			UpdateWeightForAgeInfant(l_dWeight,l_dAge,l_dSex,l_sResults);
//        		}
//        		if ((l_dAge >= 0.0) && (l_dAge <= 35.5) && (l_dWeight >= 0.0) && (l_dLength >= 0.0))
//        		{
//        			UpdateWeightForLengthInfant(l_dWeight,l_dLength,l_dSex,l_sResults);
//        		}
//        		if ((l_dAge >= 24.0) && (l_dAge <= 60.0) && (l_dWeight >= 0.0) && (l_dStature >= 0.0))
//        		{
//        			UpdateWeightForStature(l_dWeight,l_dStature,l_dSex,l_sResults);
//        		}
//        		if (l_sResults[0].equalsIgnoreCase(""))
//        			l_sResults[0] = "No results available, Verify the values you inserted.\nFor Example Age must be between 0 and 240 months.";
//        		TextView l_oResultsView = (TextView) findViewById(R.id.ResultsLbl);
//        		l_oResultsView.setText(l_sResults[0]);
//        	}
//        });
//    }

	public void decideValue(){

        Double l_dBMI = -0.1;
        Double l_dHeadCircumference = -0.1;
        Double l_dLength = -0.1;
        Double l_dStature = -0.1;
        Double l_dSex = -0.1;
        Double l_dAge = -0.1;
        Double l_dWeight = -0.1;

		String l_sResults[] = {""};
		if ((l_dWeight >= 0.0) && (l_dStature >= 24.0) && (l_dAge >= 24.0) && (l_dAge <= 240.5))
		{
			l_dBMI = l_dWeight/((l_dStature * l_dStature) / 10000);
			UpdateBMIForAge(l_dBMI,l_dAge,l_dSex,l_sResults);
		}
		if ((l_dHeadCircumference >= 0.0) && (l_dAge >= 0.0) && (l_dAge <= 36.0))
		{
			UpdateHeadForAge(l_dHeadCircumference,l_dAge,l_dSex,l_sResults);
		}
		if ((l_dAge >= 0.0) && (l_dAge <= 35.5) && (l_dLength >= 0.0))
		{
			UpdateLengthForAge(l_dLength,l_dAge,l_dSex,l_sResults);
		}
		if ((l_dAge >= 24.0) && (l_dAge <= 240.0) && (l_dStature >= 0.0))
		{
			UpdateStatureForAge(l_dStature,l_dAge,l_dSex,l_sResults);
		}
		if ((l_dAge >= 36.0) && (l_dAge <= 240.0) && (l_dWeight >= 0.0))
		{
			UpdateWeightForAge(l_dWeight,l_dAge,l_dSex,l_sResults);
		}
		if ((l_dAge < 36.0) && (l_dAge >= 0.0) && (l_dWeight >= 0.0))
		{
			UpdateWeightForAgeInfant(l_dWeight,l_dAge,l_dSex,l_sResults);
		}
		if ((l_dAge >= 0.0) && (l_dAge <= 35.5) && (l_dWeight >= 0.0) && (l_dLength >= 0.0))
		{
			UpdateWeightForLengthInfant(l_dWeight,l_dLength,l_dSex,l_sResults);
		}
		if ((l_dAge >= 24.0) && (l_dAge <= 60.0) && (l_dWeight >= 0.0) && (l_dStature >= 0.0))
		{
			UpdateWeightForStature(l_dWeight,l_dStature,l_dSex,l_sResults);
		}
		if (l_sResults[0].equalsIgnoreCase(""))
			l_sResults[0] = "No results available, Verify the values you inserted.\nFor Example Age must be between 0 and 240 months.";
	}

    public void UpdateBMIForAge(Double a_dBMI, Double a_dAge,Double a_dSex,String a_sResults[])
    {
    	Double l_dPercentage = GetPercentage(a_dBMI,a_dAge,a_dSex, bmiForAge.BMI_FOR_AGE);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "BMI For Age Percentile: " + l_dPercentage.toString() + "%\n";
    	}
    }
    public void UpdateHeadForAge(Double a_dHeadCircumference, Double a_dAge,Double a_dSex,String a_sResults[])
    {
    	Double l_dPercentage = GetPercentage(a_dHeadCircumference,a_dAge,a_dSex,headCircumferenceForAge.HEAD_CIRCUMFERENCE_FOR_AGE);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "Head For Age Percentile: " + l_dPercentage.toString() + "%\n";
    	}    	
    }
    public void UpdateLengthForAge(Double a_dLength, Double a_dAge, Double a_dSex,String a_sResults[])
    {
    	Double l_dPercentage = GetPercentage(a_dLength,a_dAge,a_dSex,lengthForAgeInfant.LENGTH_FOR_AGE_INFANT);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "Length For Age Percentile: " + l_dPercentage.toString() + "%\n";
    	}     	
    }
    public void UpdateStatureForAge(Double a_dStature, Double a_dAge, Double a_dSex,String a_sResults[])
    {
    	Double l_dPercentage = GetPercentage(a_dStature,a_dAge,a_dSex,statureForAge.STATURE_FOR_AGE);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "Stature For Age Percentile: " + l_dPercentage.toString() + "%\n";
    	}     	
    }
	public void UpdateWeightForAge(Double a_dWeight, Double a_dAge, Double a_dSex,String a_sResults[])
	{
    	Double l_dPercentage = GetPercentage(a_dWeight,a_dAge,a_dSex,weightForAge.WEIGHT_FOR_AGE);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "Weight For Age Percentile: " + l_dPercentage.toString() + "%\n";
    	} 		
	}
	public void UpdateWeightForAgeInfant(Double a_dWeight, Double a_dAge,Double a_dSex,String a_sResults[])
	{
    	Double l_dPercentage = GetPercentage(a_dWeight,a_dAge,a_dSex,weightForAgeInfant.WEIGHT_FOR_AGE_INFANT);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "Weight For Age Percentile: " + l_dPercentage.toString() + "%\n";
    	} 		
	}
	public void UpdateWeightForLengthInfant(Double a_dWeight,Double a_dLength,Double a_dSex,String a_sResults[])
	{
    	Double l_dPercentage = GetPercentage(a_dWeight,a_dLength,a_dSex,weightForLengthInfant.WEIGHT_FOR_LENGTH_INFANT);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "Weight For Length Percentile: " + l_dPercentage.toString() + "%\n";
    	} 		
	}
	public void UpdateWeightForStature(Double a_dWeight,Double a_dStature,Double a_dSex,String a_sResults[])
	{
    	Double l_dPercentage = GetPercentage(a_dWeight,a_dStature,a_dSex,weightForStature.WEIGHT_FOR_STATURE);
    	if (l_dPercentage != -0.01)
    	{
    		a_sResults[0] += "Weight For Stature Percentile: " + l_dPercentage.toString() + "%\n";
    	} 	
	}
	public Double GetPercentage(Double a_dChecking, Double a_dAgainst, Double a_dSex, Double a_dData [])
	{
		for (int l_iIndex = 0; l_iIndex < a_dData.length; l_iIndex++)
		{
			if (a_dSex.doubleValue() == a_dData[l_iIndex].doubleValue())
			{
				if (a_dAgainst.doubleValue() <= a_dData[l_iIndex+1].doubleValue())
				{
					if (l_iIndex > 5)
					{
						if (a_dAgainst.doubleValue() > a_dData[l_iIndex-4].doubleValue())
						{
							Double l_dPercentile = 0.0;
							Double l_dL = a_dData[l_iIndex + 2];
							Double l_dM = a_dData[l_iIndex + 3];
							Double l_dS = a_dData[l_iIndex + 4];
							Double l_dTemp = a_dChecking / l_dM;
							Double l_dZ = (Math.pow(l_dTemp.doubleValue(),l_dL.doubleValue()) - 1) / (l_dL * l_dS);
							Double l_dZTimesHundred = l_dZ.doubleValue() * 100;
							if (l_dZTimesHundred < 0)
							{
								l_dZTimesHundred *= -1;
								if (l_dZTimesHundred <= 309)
									l_dPercentile = 100 - (zTable.m_dZTable[l_dZTimesHundred.intValue()].doubleValue() * 100);
								else
									l_dPercentile = 0.0;
							}
							else
							{
								if (l_dZTimesHundred <= 309)
									l_dPercentile = zTable.m_dZTable[l_dZTimesHundred.intValue()].doubleValue() * 100;
								else
									l_dPercentile = 100.0;
							}
							int l_iPercentile = (int)Math.round(l_dPercentile*100);
							l_dPercentile = l_iPercentile / 100.0;
							return l_dPercentile;
						}
						else
						{
							l_iIndex+=4;
						}
					}
					else
					{
						l_iIndex +=4;
					}
				}
				else
				{
					l_iIndex +=4;
				}
			}
			else
			{
				l_iIndex +=4;
			}
		} 
		return -0.01;
	}

//    public void  showAlert(String val1,int _int,String message,String ok, boolean _boolean){
//        Context context = getApplicationContext();
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, message, duration);
//        toast.show();
//    }
}