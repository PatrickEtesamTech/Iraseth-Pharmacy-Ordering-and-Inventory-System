package com.example.irasethpharma;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class HomeProductList
{

    public static String[] productNames = new String[]{"Blood Bank Refrigerator", "Blood Refrigerator AF140-E",
            "Frimed Combined Refrigerator Freezer AF140C-2", "Frimed Mortuary and Hospital Cold Rooms",
            "Laboratory Freezer AF140B-3", "Laboratory Refrigerator AF70-2", "Laboratory Refrigerator SB10B-1", "Medical Freezer", "Outdoor Cooler Equipment", "Coolermed AC25FR"};
    public  static double[] productPrice = new double[]{581274.88, 776331.58, 68014.00, 111336.01, 100000.00, 100000.00, 100000.00, 305232.12, 50100.55, 55255.12};
    public static int[] productImages = new int[]{R.drawable.ref_bloodbankrefrigerator_af70e, R.drawable.ref_bloodrefrigerator_af140e_4,
            R.drawable.ref_frimed_combinerefrigerator_freezers_af140c_2, R.drawable.ref_frimed_mortuaryandhospitalcoldrooms,
            R.drawable.ref_laboratoryfreezer_af140b_3, R.drawable.ref_laboratoryrefrigerator_af70_2, R.drawable.ref_laboratoryrefrigerator_sb10b_1,
            R.drawable.ref_medicalfreezer, R.drawable.outdoor_cooler, R.drawable.ref_coolermedac25fr};
    public static String[] productBrand = new String[] {"Coolermed", "Fremed", "Fremed", "Fremed", "Fremed", "Fremed", "Fremed", "Coolermed", "Coolermed", "Coolermed"};
    public static String[] getProductModel = new String[] {"M260KN – 225 Liter", "AF140E", "AF140C", "RD-98", "AF140B", "AF170",
            "SB10B", "M180FR – 175 Liter", "Solar Box", "AC25FR"};
    public static String[] getProductDetails = new String[] {
            "• Outer Dimensions WxDxH (mm):  590x635x1606.5\n" +
                    "• Internal Dimensions WxDxH (mm):  491x489x993\n" + "Operating Temperature:  0 / +15 °C\n" +
                    "• Set Temperature:  4 °C\n" +
                    "• Voltage:  220 / 240 V\n" +
                    "• Frequency:  50Hz\n" +
                    "• Power Consumption (24h/Watt): 1.29",

            "• 14 drawers in Scotch Brite stainless steel\n" +
                    "• four unidirectional wheels with stabilizing feet\n" +
                    "• key lock\n" +
                    "• 3x1 separable power cable complete with Schuko plug (on request English plug)\n" +
                    "• electric anti-interference filter placed inside the motherboard\n" +
                    "• protection fuses\n" +
                    "• switching on and off with a password\n" +
                    "• electronic control panel with alarm system\n" +
                    "• contact for remote alarm\n" +
                    "• USB port\n" +
                    "• alarm memory",

            "• 4 shelves in refrigerator compartment\n" +
                    "• 4 shelves in freezer compartment\n" +
                    "• Four one way wheels with stabilizing adjustable feet\n" +
                    "• 2 locks with key\n" +
                    "• Power cord with Schüko type plug (or British plug on request)\n" +
                    "• Protection fuses (on power switch and electronic mainboard)\n" +
                    "• RFI filter\n" +
                    "• Password protected power switch and settings\n" +
                    "• Electronic control panel with alarm system\n" +
                    "• Remote contact (dry, volt-free) for alarm\n" +
                    "• Data logger with USB port to download readings and data\n" +
                    "• Memory of latest alarms visible on screen\n" +
                    "• Power failure at electricity recovery",

            "• Structure and insulation Prefabricated modular panels externally and internally made out of hot dip galvanized steel, white PVC coated, scratch resistant, \n" +
                    "washable and easy to clean. Stainless steel external and internal surface upon request. Insulation of 60-80 mm obtained by injection of high density ecological polyurethane foam, \n" +
                    "• CFC free. Floor and ceiling with internal rounded edges by means of joints and profiles, with installation system of simple and quick application. Profiles can be easily replaced in case \n" +
                    "of damage and allow internal hygiene, in compliance with the sanitary regulations.\n" +
                    "• Doors or hatches  Doors or hatches depending on the model. Hinges of heavy-duty manufacturing. Replaceable gaskets. Ergonomic lock with key.\n" +
                    "• Body tray with castors  Concave trays for loading and storing bodies; designed with borders, castors and two handles, entirely made of AISI 304 stainless steel.\n" +
                    "• Internal storage racking  Internal storage racking made of AISI 304 stainless steel, easy to assemble and disassemble. Support with telescopic slides on request.\n" +
                    "• Temperature range Cold rooms are available with positive temperature range from +2°C to +10°C and with negative temperature range from 0° to -10°C/ -20°C according to their application.\n" +
                    "• Refrigeration system Monobloc units fitted on wall or on ceiling as standard. On request it is possible to install the monobloc unit with remote command also outside the room. Completely \n" +
                    "sealed, silent and highly efficient hermetic compressor and ventilated air condenser (suitable also for tropical countries). Internal ventilated evaporator. This system forcing air circulation \n" +
                    "ensures to provide uniform temperature inside the cold room. CFC-free refrigerant gas.\n" +
                    "• Defrosting  Automatic defrosting cycle with evaporation of condensate water.",

            "• 8 shelves\n" +
                    "• four unidirectional wheels with stabilizing feet\n" +
                    "• key locks\n" +
                    "• 3x1 separable power cable complete with schuko plug (on request English plug)\n" +
                    "• electric anti-interference filter\n" +
                    "• protection fuses\n" +
                    "• switching on and off with a password\n" +
                    "• electronic control panel with alarm system\n" +
                    "• contact for remote alarm\n" +
                    "• USB port\n" +
                    "• alarm memory",

            "• 14 drawers in Scotch Brite stainless steel\n" +
                    "• four unidirectional wheels with stabilizing feet\n" +
                    "• key lock\n" +
                    "• 3x1 separable power cable complete with Schuko plug (on request English plug)\n" +
                    "• electric anti-interference filter placed inside the motherboard\n" +
                    "• protection fuses\n" +
                    "• switching on and off with a password\n" +
                    "• electronic control panel with alarm system\n" +
                    "• contact for remote alarm\n" +
                    "• USB port\n" +
                    "• alarm memory",

            "• 2 drawers in Scotch-Brite stainless steel\n" +
                    "• four unidirectional wheels with stabilizing feet\n" +
                    "• key lock\n" +
                    "• 3x1 separable power cable complete with Schuko plug (on request English plug)\n" +
                    "• electric anti-interference filter\n" +
                    "• protection fuses\n" +
                    "• switching on and off with a password\n" +
                    "• electronic control panel with alarm system\n" +
                    "• contact for remote alarm\n" +
                    "• USB port\n" +
                    "• alarm memory",

            "• Outer Dimensions WxDxH (mm):  517x569x2115\n" +
                    "• Internal Dimensions WxDxH (mm):  357x363x1410\n" +
                    "• Net Volume (L):  175\n" +
                    "• Gross Volume (L):  180\n" +
                    "• Refrigerant: R290\n" +
                    "• Outer Body Color: RAL7047\n" +
                    "• Shelves - Drawers: 6 pcs\n" +
                    "• 13.6 Meter Truck Loading Quantity: 100\n" +
                    "• Insulation Thickness (mm): 80\n" +
                    "• Operating Temperature (Cooler): -5 / -30 °C\n" +
                    "• Set Temperature: -25 °C\n" +
                    "• Voltage: 220 / 240 V\n" +
                    "• Frequency: 50Hz\n" +
                    "• Power Consumption (24h/Watt): 3.5",


            "• In areas where there is no electricity network or access is limited, you can generate electricity with solar energy panels and run your cooler with the help of batteries.\n" +
                    "Technical Specifications:\n" +
                    "\n" +
                    "• External Dimensions (w x d x h): 1295 x 819 x 232\n" +
                    "• Net Weight (kg): 65\n" +
                    "• Open Size (w x d):  1567 x 1267\n",

            "• Gross volume (l): 133\n" +
                    "• Set temperature: - 20 °C\n" +
                    "• Operating range: -10 / -22 °C\n" +
                    "• First cooling time (from 32 ° C + 4 ° C): -\n" +
                    "• Climate zone: ST/T (32 ° C)\n" +
                    "• External dimensions W x D x H (mm): 836.5x853x975\n" +
                    "• Internal dimensions W x D x H (mm): 531x512x566.5\n" +
                    "• Shipping dimensions W x D x H (mm): 880x900x1060\n" +
                    "• Shipping weight (kg): 97\n" +
                    "• Net weight (kg): 80\n" +
                    "• Power consumption (kwh / 24h): -\n" +
                    "• Refrigerant type and quantity: R600a /R134a\n" +
                    "• Control unit: Microprocessor\n" +
                    "• Energy Source: 12 / 24 V DC"};

    // Radiology Equipment Data
    public static String[] radiologyItemsName = new String[]{"Anesthesia Machine", "Digital Radiography System", "Full Field Digital Mammography System 1",
            "Full Field Digital Mammography System 2", "Fluoroscopy", "Mobile C Arm 3", "Mobile C Arm 4", "Radiography System", "Magnetic Resonance Imaging", "Mobile X-ray System"};
    public static int[] radiologyItemsImage = new int[]{R.drawable.rad_anesthesia_machine_2, R.drawable.rad_digital_radiography_system_2,
            R.drawable.rad_fullfield_digital_mammography_system_1, R.drawable.rad_fullfield_digital_mammography_system_2,
            R.drawable.rad_fluoroscopy_1, R.drawable.rad_mobile_c_arm_3, R.drawable.rad_mobile_c_arm_4, R.drawable.rad_radiography_system_2, R.drawable.magnetic_resonance_imaging, R.drawable.mobile_x_ray_system};
    public static double[] radiologyProductPrice = new double[]{533856.14, 87978.68,  380627.18, 480627.18, 7515.18, 593226.91, 693226.91, 75978.68, 148850.38,80718.60};
    public static String[] radiologyBrand =  new String[]{"Sternmed", "Sternmed", "Sternmd", "Sternmed", "Sternmed", "Sternmed", "Sternmed", "Sternmed", "Sternmed", "Sternmed"};
    public static String[] radiologyModel =  new String[]{"Acacs 50", "Xenon Rad 400", "Xenon S100", "Xenon S200", "Xenon RF1000", "Xenon C300 & C400", "IPN 56", "Xenon Rad 400", "Marcom 0.35T", "Xenon M70"};

    public static String[] radiologyDetails =  new String[]{"• Ventilator E-vent Electronically controlled, pneumatically driven\n" +
            "• Operating Modes VCV, PCV, SIMV(V)+PS, SIMV(P)+PS, Sigh, Cardio pulmonary\n" +
            "• Bypass, Spont/CPAP, PRVC, Standby, Manual\n" +
            "• Breathing frequency 1-100bpm\n" +
            "• Minute volume (MV) 99 l/min (monitoring), 30L/min (setting)\n" +
            "• Positive end-expiratory pressure (PEEP) OFF, 4-30 cm\n" +
            "• Inspiration / Expiration ratio (Ti:Te) 4:1-1:9 (increment:0.5)\n" +
            "• Pressure limiting (Pmax) PEEP+5-PEEP+70 cm H2O\n" +
            "• Tidal Volume (Vt) 20-1500ml\n" +
            "• Inspiratory pause (Tip:Ti) OFF, 5%-50%\n" +
            "• SIMV Inspiratory time (Tinsp) 1-10S (increment:0.1S)\n" +
            "• Inspiratory pressure (Pinsp) PEEP+5-PEEP+70 cm H2O\n" +
            "• Inspiratory flow (InspFlow) 10-80 L/min\n" +
            "• Pressure Support Level (PPS) 5-60 cm H2O\n" +
            "• Frequency for and “OFF” Apnea-Ventilation (Freq.Min.) 1-100 bpm\n" +
            "• Trigger 1-15L/Min\n" +
            "• Range of fresh gas flow indicators 0-15 l/min\n" +
            "• Total fresh gas flow meter 0-15 L/min with a mixture of O2, N2O and Air\n" +
            "• Vaporizer Mount Selectatec ® 2 position either Drager mount or selectatec\n" +
            "• Continuous monitoring of aspiratory O2 concentration, breathing frequency, tidal volume, minute volume, mean or plateau pressure, peak\n" +
            "• Airway pressure as well as PEEP. In addition, all fresh gas flow information is displayed in numbers\n" +
            "• VGA, RS232, RJ45, Debugging communications interface\n" +
            "• Volume of CO2 absorber 1.5 liter\n" +
            "• Volume of entire compact breathing system 2 liter + bag\n" +
            "• Battery (supports ventilator and monitor) min. 60 minutes",

            "• Lateral range of motion 0～1800mm\n" +
                    "• Vertical range of motion 200mm（Manual）\n" +
                    "• Rotation angle (horizontal axis) of the tube -45 ° ~ 90 °, 0 ° and 90 ° with auto-stop function\n" +
                    "• X-ray tube rotation angle (vertical axis) ± 135 °, every 90 ° locking (manual)\n" +
                    "• Range of detectors movement 400~1820mm\n" +
                    "• Detector rotation angle -20 ° ~ 90 °, electric\n" +
                    "• Remote control motion control with remote control function table\n" +
                    "• The max. loading capacity of the bed is 200Kg\n" +
                    "• Carbon fiber bed panel\n" +
                    "• Brake mode aster brakes\n" +
                    "• Tube voltage range 40~150kV\n" +
                    "• Focus 0.6/1.2mm\n" +
                    "• Anode rotation speed 2700rpm\n" +
                    "• Heat capacity 300kHU\n" +
                    "• Cooling method air-cooled\n" +
                    "• Photography current 10-640mA\n" +
                    "• Generator power 50kW\n" +
                    "• Photography time 1ms-10s\n" +
                    "• Highest KV 150KV\n" +
                    "• Grid control",

            "• Generator output power 5 kW (at 35 kV) \n" +
                    "• kV range 20/35 kV (20/40 kV optional) \n" +
                    "• kV resolution (Man & Auto mode) 0.5 kV \n" +
                    "• Lowest current time 1 mAs \n" +
                    "• mAs maximum value 640 mAs \n" +
                    "• mAs resolution (automatic) 0.1 mAs \n" +
                    "• Exposure time 02/9 s (is automatically selected depending on the selected mAs) \n" +
                    "• Safety timer 10s \n" +
                    "• Standard collimation plate 18 × 24 cm and optional collimation plates 24 × 30 cm \n" +
                    "• Optional automatic collimator 18 × 24 cm / 24 × 30 cm \n" +
                    "• Graphic display 240 × 128 points \n" +
                    "• Anti-scatter grid formats 18 × 24 cm (standard) 24 × 30 cm (optional) \n" +
                    "• Type Grid type linear, oscillating \n" +
                    "• Manual density control 11 steps 0 +/- 5 \n" +
                    "• 13 programmable settings for different film-foil combinations \n" +
                    "• Short-term stability measured over 10 exposures at 28 kV 50 mAs <3%. \n" +
                    "• Motorized vertical movements \n" +
                    "• Rotation (manual or optionally motorized) \n" +
                    "• +/- 15 ° rotation (only with isocentric 3D) \n" +
                    "• Vertical range of motion (from the floor) 75 to 160 cm (stroke 85 cm) \n" +
                    "• Range of the C-arm rotation +/- 180 ° \n" +
                    "• Clockwise, counterclockwise continuous motorization (optional) \n" +
                    "• Speed \u200Bof the C-arm rotation in the motorized version 90 ° / 8 s with acceleration and deceleration ramp   \n" +
                    "• Standard compression paddle 18 × 24/24 × 30 for normal breasts \n" +
                    "• Optional compression paddle moved 24 × 30 cm for large breasts \n" +
                    "• Compression paddle for quick unlocking \n" +
                    "• Pressure force adjustable from 70 to 200 N",

            "• Indirect Conversion\n" +
                    "• detector Technology: a-Si TFT Array + PIN Photodiode\n" +
                    "• Optional Direct Conversion\n" +
                    "• Full Field Flat Panel Detector FPD technology: Amorphous Selenium (a-Se)\n" +
                    "• Generator Output Power 5 kW (@ 35 kV)\n" +
                    "• kV range 20 / 35 kV (20/40 kV optional)\n" +
                    "• kV resolution (Man & Auto mode) 0,5 kV\n" +
                    "• Lowest Current Time 1 mAs\n" +
                    "• mAs maximum value 640 mAs\n" +
                    "• mAs resolution (Automatic) 0,1 mAs\n" +
                    "• Exposure Time 02/4.7 s (Automatically selected in function of selected mAs)\n" +
                    "• Safety timer 10s\n" +
                    "• Automatic collimator 18×24 cm/24×30 cm/10×24 for magnification\n" +
                    "• Automatic filtration (Rh/Ag)\n" +
                    "• Display GRAPHIC LCD Display 240×128 dots\n" +
                    "• Automatic Exposure Control (AEC)\n" +
                    "• Vertical Movements motorized\n" +
                    "• Motorized Rotation\n" +
                    "• +/- 15° Rotation for biopsy procedure\n" +
                    "• Range of Vertical Movement (from Floor) 75 to 160 cm (travel of 85 cm)\n" +
                    "• Range of C-Arm Rotation +/-180°\n" +
                    "• Speed of C-Arm Rotation in motorized version 90°/8 s with acceleration and deceleration ramp\n" +
                    "• Standard Compression Paddle 18×24/24×30 for normal breasts\n" +
                    "• Optional Compressor Paddle: 8×24 cm with lateral shifting for normal breasts ,9×21 cm straight for magniﬁcation,\n" +
                    "• Φ7,5 cm shifted for spot contact examination and\n" +
                    "• 18×24 cm shifted for bidimensional biopsy",

            "• Patient weight up to 300 kg\n" +
                    "• Tilting table with progressive starting +90°/ -20°\n" +
                    "• Tilting speed 4 °/sec\n" +
                    "• Lateral movement (max. speed 3 cm/sec) ±11.5 cm\n" +
                    "• Plane-film distance 7.5 cm\n" +
                    "• Film-image-intensifier distance 7.5 cm\n" +
                    "• Movement column (max. speed 13 cm/sec) 140 cm\n" +
                    "• X-ray tube rotation manually ± 90°\n" +
                    "• Focus-film distance (max. speed 3 cm/sec) from 107 cm to 153 cm\n" +
                    "• Tube angulation ±30°\n" +
                    "• Motorized compression 70n / 80n / 90n\n" +
                    "• Step by step angiography\n" +
                    "• Prepared to receive image-intensifier of 9″, 12″, 14″ &16″\n" +
                    "• Tomographic function\n" +
                    "• Linear tomographic plain-plain\n" +
                    "• Tomographic angles 8° – 15° – 30° – 40°\n" +
                    "• 4 selectable tomographic speeds\n" +
                    "• S.F.D. electronic automatic\n" +
                    "• S.F.D. allows rapid series exposures\n" +
                    "• Cassettes from in both directions from 18×24 cm to 35×43 cm\n" +
                    "• Transition time between Fluoro and radiography about 1 sec.\n" +
                    "• Motorized scattered grid\n" +
                    "• On a single film is possible to have Radio and Tomo exposures\n" +
                    "• Collimator predisposition check compatibility with X-ray tube\n" +
                    "• Tube predisposition check compatibility with collimator",

            "• XENOX C400\n" +
                    "• Xenox C400 (with flat screen) 30 × 30 or 21 × 21 cm \n" +
                    "• 12 \"\"and 9\"\" image intensifier options available as Xenox C300 \n" +
                    "• Digital memory: 1.5k x 1.5k, with recording up to 25 fps (for Xenox C400); 1kx1k, with recording up to 30 fps (for Xenox C300) \n" +
                    "• 30 kW HV generator \n" +
                    "• DUAL COOLING system for immediate and effective heat dissipation \n" +
                    "• DUAL POWER System: power reserve system \n" +
                    "• E-motion system: fully motorized C-arm movements (optional) \n" +
                    "• 215 mm horizontal run (17 mm in motorized version) \n" +
                    "• Broad orbital movement 150 ° \n" +
                    "• C-arm, lateral rotation: ± 180 ° \n" +
                    "• 10 “touchscreen control panel\n" +
                    "• Horizontal run: manual movement 210 mm; E-motion 175 mm \n" +
                    "• Motorized vertical run: 500 mm; 500 mm; 500 mm \n" +
                    "• C-arm LATERAL rotation: ± 140 ° (± 180 ° *); + 180 °; - 90 ° (-180 ° manual) \n" +
                    "• C-arm ORBITAL movement: 150 ° (105 °, -45 °); 150 ° (105 °, -45 °) \n" +
                    "• TIG: ± 10 °; ± 10 °; ± 10 ° \n" +
                    "• Usable space: 810 mm; 810 mm; 810 mm \n" +
                    "• C-arm depth: 640 mm; 640 mm; 640 mm \n" +
                    "• Min / Max Height: 1455/1955 mm; 1455/1955/1955 mm \n" +
                    "• Min / Max length: 2085/2295 mm; 2110/2285 mm \n" +
                    "• Maximum width: 800 mm; 800 mm; 800 mm \n" +
                    "• S.I.D.: 1065 mm; 1065 mm; 1065 mm \n" +
                    "• Measurement without mechanical safety block\n" +
                    "• XENOX C300\n" +
                    "• Horizontal run: manually adjustable 9 ″ 210 mm; E-Motion 9 ″ 175 mm; E-Motion 12 ″ 210 mm \n" +
                    "• Motorized vertical run: 500 mm; 500 mm; 500 mm; 460 mm; 460 mm \n" +
                    "• C-arm LATERAL rotation: ± 140 ° (± 180 ° *); + 180 ° - 90 ° (-180 ° manual); + 180 ° - 90 ° (-180 ° manual) \n" +
                    "• C-arm ORBITAL movement: 135 ° (90 ° + 45 °); 135 ° (90 ° + 45 °); 115 ° (90 ° + 25 °) \n" +
                    "• TIG: ± 10 °; ± 10 °; ± 10 °; ± 10 °; ± 10 ° \n" +
                    "• Usable space: 755mm; 755mm; 755mm; 708mm; 708mm \n" +
                    "• C-arm depth: 665mm; 665mm; 665mm; 640mm; 640mm \n" +
                    "• Min / Max height: 1750/2250 mm; 1750/2250 mm; 1750/2250 mm; 1805 / 2265mm \n" +
                    "• Min / Max length: 2075/2285 mm; 2100/2275 mm; 2118/2293 mm \n" +
                    "• Maximum width: 800 mm; 800 mm; 800 mm; 800 mm; 800 mm \n" +
                    "• S.I.D.: 1029 mm; 1029 mm; 1029 mm; 1009mm \n" +
                    "• Measurement received without mechanical safety block\n" +
                    "• APPLICATION\n" +
                    "• Electrophysiology studies \n" +
                    "• Angiographic procedures \n" +
                    "• Vascular surgery \n" +
                    "• Endovascular applications \n" +
                    "• Urology procedures \n" +
                    "• Catheter procedures \n" +
                    "• Neuroradiology \n" +
                    "• General surgery \n" +
                    "• Traumatology \n" +
                    "• Orthopedic \n" +
                    "• Interventional radiology",

            "• Flow rate 0.1~1500 mL/h\n" +
                    "• Bolus rate600~1000 mL/h\n" +
                    "• KVO rate 0.1~5 mL/h\n" +
                    "• Ultrasonic Bubble detection with sensitivity of 25µl\n" +
                    "• Infusion history 15000 records\n" +
                    "• Preset volume 1~9999 mL\n" +
                    "• Display range of totally delivered volume 0~9999 mL\n" +
                    "• Power AC110~230V, 50~60 Hz\n" +
                    "• Inner battery: DC12V, 2300Mah\n" +
                    "• 6 hours battery operation at the rate of 25mL/h\n" +
                    "• IPX4 Waterproof level\n" +
                    "• Wireless communication\n" +
                    "• Infusion mode setting Rate mode, drip mode, weight and time mode\n" +
                    "• 3 mode Infusion rates: Drip/min, mL/h, time/volume\n" +
                    "FEATURES\n" +
                    "• Tube heating option\n" +
                    "• Dual-CPU monitoring function\n" +
                    "• Body-weight mode conversion\n" +
                    "• Pressure dynamic display\n" +
                    "• Multi-direction clamp for either horizontal or vertical fixation\n" +
                    "• 3-level occlusion alarm limits\n" +
                    "• Ultrasonic bubble detection\n" +
                    "• Infusion apparatus calibration\n" +
                    "• Nurse call interface\n" +
                    "• KVO function\n" +
                    "• Wireless communication function\n" +
                    "• Night mode lighting\n" +
                    "• Smart pulsation compensation function\n" +
                    "• Detection of drip sensor\n" +
                    "• Fast rate control\n" +
                    "• External DC power interface\n" +
                    "• AC and internal power indication\n" +
                    "• Standard RS232 interface\n" +
                    "• 1500 history records\n" +
                    "• Waterproof",

            "• Lateral range of motion 0～1800mm\n" +
                    "• Vertical range of motion 200mm（Manual）\n" +
                    "• Rotation angle (horizontal axis) of the tube -45 ° ~ 90 °, 0 ° and 90 ° with auto-stop function\n" +
                    "• X-ray tube rotation angle (vertical axis) ± 135 °, every 90 ° locking (manual)\n" +
                    "• Range of detectors movement 400~1820mm\n" +
                    "• Detector rotation angle -20 ° ~ 90 °, electric\n" +
                    "• Remote control motion control with remote control function table\n" +
                    "• The max. loading capacity of the bed is 200Kg\n" +
                    "• Carbon fiber bed panel\n" +
                    "• Brake mode aster brakes\n" +
                    "• Tube voltage range 40~150kV\n" +
                    "• Focus 0.6/1.2mm\n" +
                    "• Anode rotation speed 2700rpm\n" +
                    "• Heat capacity 300kHU\n" +
                    "• Cooling method air-cooled\n" +
                    "• Photography current 10-640mA\n" +
                    "• Generator power 50kW\n" +
                    "• Photography time 1ms-10s\n" +
                    "• Highest KV 150KV\n" +
                    "• Grid control\n" +
                    "• AEC in three directions\n" +
                    "• Detector Pixel matrix 3072(h)X3072(v)\n" +
                    "• Image display time is 8s",

            "• Permanent magnet with automatic constant temperature system\n" +
                    "• Field strength 0.35T\n" +
                    "• C-shape magnet\n" +
                    "• Homogeneity ≤2.5ppm\n" +
                    "• Shimming method Active/Passive/Dynamic\n" +
                    "• Optimal algorithm of active shimming\n" +
                    "• Magnet vertical gap 40cm\n" +
                    "• Accessibility (Horizontal opening angle) >270°\n" +
                    "• Gradient field strength (Single Axis) 25mT/m\n" +
                    "• Gradient slew rate (Single Axis) 75mT/m/ms\n" +
                    "• FOV 20 to 400mm\n" +
                    "• 2D Thickness 1mm\n" +
                    "• 3D thickness 0.1mm\n" +
                    "• Air cooling gradient system\n" +
                    "• Fully digital Spectrometer\n" +
                    "• Digital Transmit and Receive RF system\n" +
                    "• 4 channels RF system\n" +
                    "• Phase array coil\n" +
                    "• Dual-voice- patient communication system\n" +
                    "• patient weight max. 200Kg\n" +
                    "• Patient bed laser location system\n" +
                    "• DICOM 3.0\n" +
                    "• Fully open Magnet",

            "• Generator power 4KW\n" +
                    "• Stationary anode\n" +
                    "• 200 mAs\n" +
                    "• 40 to 110 kV voltage\n" +
                    "• 6 to 0.75mm small focus\n" +
                    "• 25to 1.3mm large focus\n" +
                    "• Manual operation\n" +
                    "• 43x43cm Rad field\n" +
                    "• 6 cassettes storage\n" +
                    "• LCD display 20×4 characters\n" +
                    "• Collimator with laser pointer and pediatric filter"};





    // Furniture Data
    public static String[] furnitureItemsName = new String[]{"Mechanical Patient Bed", "Doctor S Chair 1", "Doctor S Chair 2", "Electric Dialysis Chair", "Electric LDR Bed",
            "Electric ICU Hospital Bed", "Function Electric ICU Bed", "Trolley IV Stand", "Stretcher", "Manual Dialysis Chair"};
    public static int[] furnitureItemsImage = new int[]{R.drawable.fur_bed_wside_rails_and_mattress_mechanical_patient_bed_with_lock, R.drawable.fur_doctor_single_chair_1,
            R.drawable.fur_doctor_single_chair_2, R.drawable.fur_electric_dialysis_chair, R.drawable.fur_electric_ldr_bed, R.drawable.fur_electric_icu_hospital_bed,
            R.drawable.fur_fucntion_electric_icu_bed, R.drawable.fur_patient_record_trolley_iv_stand, R.drawable.fur_stretcher, R.drawable.fur_manual_dialysis_chair};
    public  static double[] furnitureProductPrice = new double[]{29000.00, 5306.00, 5455.46, 133046.52, 105007.20, 155007.20, 49999.00, 19507.16, 20500.00, 30907.15};


    public static String[] furnitureProductBrand =  new String[]{"Mingtai", "-", "-", "Bestran", "Mingtai", "Bestran", "Bestran", "Bestran", "Mengtaimed", "Bestran"};
    public static String[] furnitureProductModel =  new String[]{"S2 Double Crank hospital bed", "MT-ZY-01", "MT-ZY-07", "BT-DY016", "BT-LD001", "M7-5", "BT-AE036", "BT-CHY003", "Z2", "BT-DY004"};

    public static String[] furnitureProductDetails =  new String[]{"•Size:2080 x 900 x 500 mm \n" +
            "• With ABS head and foot board \n" +
            "• With aluminum side rail\n" +
            "• With 5\" deluxe castor with brakes \n" +
            "• With I.V stand and four I.V stand sockets with drainage bag holders each side\n" +
            "• With apparatus rack under the bed \n" +
            "• Two functions adjusted by hand crank: \n" +
            "• Back rest lifting angle: 80°±5° \n" +
            "• Knee rest lifting angle: 45°±5° ·\n" +
            "• Bearing capacity: ≥200kg",

            "• Material: Microfiber leather \n" +
                    "• Electroplating five star feet \n" +
                    "• PU castor \n" +
                    "• Sitting surface diameter 38CM \n" +
                    "• height adjusted from 48~61cm \n" +
                    "• packing size:（CM)48*48*20",

            "• Material: Microfiber leather \n" +
                    "• Aluminum alloy five star foot, import wheel \n" +
                    "• backrest type \n" +
                    "• lifting range：48~62CM \n" +
                    "• Package size:（CM)60*60*25",

            "• Two L&K motors to adjust chairs backrest and leg rest, electric CPR \n" +
                    "• Backrest adjustment: -10°～75°±5° \n" +
                    "• Leg rest adjustment: -80°～12°±5° \n" +
                    "• Trendelenburg: -12° \n" +
                    "• Complete size: L192 x W85 x H56cm \n" +
                    "• N.W.: 70KG  \n" +
                    "• Weight load:200 \n" +
                    "• Steel frame, PVC mattress \n" +
                    "• 4 silent wheels with brakes, dia 125mm",

            "• Bed dimension including bed end and bumper wheel: Length 1930mm, width 940mm \n" +
                    "• Bed dimension: Length 1815mm, width 780mm \n" +
                    "• Min. & Max. table height: 620mm~860mm(exclude the cushion) \n" +
                    "• Back section turning: ≥60° \n" +
                    "• Trendelenburg and reverse trendelenburg: reverse trend≥5° trend≥8° \n" +
                    "• Seat section turning: -12°~32° \n" +
                    "• Foot rest swing out: ≥45 \n" +
                    "• Foot rest folding: ≥45 \n" +
                    "• Handrail up/down movement: 360mm \n" +
                    "• Castor Diameter: 150mm \n" +
                    "• Standard Accessories: \n" +
                    "• Leg holder 1 pair  \n" +
                    "• Filth Basin 1 piece \n" +
                    "• I.V. Stand 1 piece \n" +
                    "• Hand control 1piece \n" +
                    "• Power cord 1 piece",

            "• MS perforated platform with ventilation hole, \n" +
                    "• molded ABS head/foot board, \n" +
                    "• detachable ABS tuck-away side rail, 4 pieces \n" +
                    "• Embedded control on the side rail \n" +
                    "• One button electric CPR \n" +
                    "• Manual CPR Function \n" +
                    "• 4 small wheels at each side to prevent bed \n" +
                    "• 4 Heavy duty caster, dual sides with diameter of 125mm, one of the castors is steering \n" +
                    "• Central brake system \n" +
                    "• Battery backup \n" +
                    "• oxygen cylinder holder \n" +
                    "• I.V. pole\n" +
                    "• Urine bag hook\"",

            "• Overallsize: 2175mm±10mm (Stretchable length 2320)\n" +
                    "• Overall width: 990mm \n" +
                    "• Frame: steel, treated by sand-balsting and electrostatic powder-coating \n" +
                    "• Headboard/footboard:ABS,Detachable \n" +
                    "• Side rail: ABS, split up and down, safety guardrail \n" +
                    "• Rail controller: embedded on side rail at head side \n" +
                    "• Handset controller: hand-held remote control \n" +
                    "• Nurse controller: embedded on footboard \n" +
                    "• Motor: Linak motor from Denmark,with backup batteries \n" +
                    "• Manual CPR handles at head side \n" +
                    "• Binding device:5 pcs each side,under the bed board\n" +
                    "• Castors: four Central-controlled silent wheels, φ125mm \n" +
                    "• Four foot pedals at each side:all functions can be operated by the pedals on both side \n" +
                    "• Angle indicators:ball type angle indicator \n" +
                    "• Standard accessories:I.V pole 1pc, IV pole holes 4pcs, Drainage hooks 2pcs \n" +
                    "• 5-function:\n" +
                    "• Back up 0-75° \n" +
                    "• Knee up 0-45° \n" +
                    "• Height adjustment:420-800mm\n" +
                    "• Trendelenburg: 0-16° \n" +
                    "• Anti-trendelenburg:0-16°\"",

            "• Size: 670*370*1050mm \n" +
                    "• 304 stainless steel material \n" +
                    "• Double rows, 48 layers with 2 drawers \n" +
                    "• Four luxurious noiseless casters, two with brakes",

            "• Size: 1930*640*500-900mm \n" +
                    "• Material: made of quality aluminum alloy. Cart surface and side rails are shaped by once injection ABS plastics. \n" +
                    "• Advanced centrally controlled brake system is applied to the cart and therefore stable and reliable. \n" +
                    "• The back rest section supported by the imported pneumatic spring. \n" +
                    "• Manual crank adjust the height. \n" +
                    "• Castors 5\"\" single side \n" +
                    "• Basic parameters \n" +
                    "• Back section panel:≥75°\n" +
                    "• Height-adjustment 500-900mm \n" +
                    "• Safe load:180kg",

            "• Spring gas to control the chairs back and leg  \n" +
                    "• Chair size: 1900L*830W*1350H-1680mm  \n" +
                    "• Back: -10°---80° ·\n" +
                    "• Leg  10°-- -80° ·\n" +
                    "• IV Pole Option·\n" +
                    "• Standard blue\n" +
                    "• Viny Upholstery"
    };
    //Nursery Data
    public static String[] nurseryItemsName = new String[]{"ARI Double Infant PhotoTherapy Unit", "ARI Electronic Infant Scale", "ARI Infant CPAP System",
            "ARI Infant Incubator", "ARI Infant Ventilator", "Bestran Baby Cart", "Melison New Born Hearing Screener", "Mingtai Flat Children Bed"};
    public static int[] nurseryItemsImage = new int[]{R.drawable.nur_ari_doubleinfantphototherapyunit_al_200led, R.drawable.nur_ari_electronicinfantscale_acs_20bye, R.drawable.nur_ari_infantcpapsystem_ar_200d,
            R.drawable.nur_ari_infantincubator_ai_3, R.drawable.nur_ari_infantventilator_ar_y200, R.drawable.nur_bestran_babycart_bt_ab102, R.drawable.nur_melison_newbornhearingscreener,
            R.drawable.nur_mingtai_flatchildrenbedwithmattress};
    public  static double[] nurseryProductPrice = new double[]{333451.33, 41473.00, 222115.33, 45582.41,122115.33, 10389.00, 10635.90, 9040.00};

    public static String[] nurseryProductBrand =  new String[]{"Ari", "Ari", "Ari", "Ari", "Ari", "Bestran", "Melison", "Mingtai"};

    public static String[] nurseryProductModel =  new String[]{"AL-200LED", "ACS 20bye", "AR 200d", "AI 3", "AR y200", "BT-AB102", "-", "-"
    };


    public static String[] nurseryProductDetails =  new String[]{"• Double side phototherapy for more effective radiation\n" +
            "• Upside phototherapy and downside phototherapy can be used separately\n" +
            "• Three levels to adjust the irradiation: Low, Medium, High\n" +
            "• LCD screen of upside phototherapy unit displays therapy time with timer and countdown\n" +
            "• Downside phototherapy unit adopts blue LED bulbs source and independent air-cooling fan\n" +
            "• Upside phototherapy unit adopts big blue LED bulbs, uniform, efficient and long life\n" +
            "• Protect boards of infant bed could be folded down for clinical use\n" +
            "• Light head of upside phototherapy unit can be adjusted 360° horizontally and 180°vertically\n" +
            "• Stand column could be adjusted ±360°\n" +
            "• Anti-rusty aluminum alloy base\n" +
            "• High irradiation, easy to operation and move\n" +
            "• With drawers and lockable castors\n" +
            "• The height of upside phototherapy is adjustable\n" +
            "• Automatically record total used therapy time",

            "• Max weighing: 20kg/44lbs\n" +
                    "• Min weighing: 200g/0.5oz\n" +
                    "• Division: 10g/0.02lb\n" +
                    "• Display: 5 digital LCD\n" +
                    "• Power: 6F22ND 9V\n" +
                    "• Height range to be measured for baby: 0-56cm/0-22\"\"\n" +
                    "• Min value of height per division: 1mm/ 1/16\"\"\n" +
                    "• Inbox size: 63.5×43×21cm (Color Box) /1pc\n" +
                    "• G.W.: 5kg; N.W.: 4kg\n" +
                    "• Shipping carton size: 89×66×43cm /4 pcs\n" +
                    "• G.W.: 22kg; N.W.: 20kg ",

            "• Integration breathing circuit design, ensure easy operating and keep tidy.\n" +
                    "• Real time pressure-time graphics and high precision O2 concentration detection function included.\n" +
                    "• Built-in air and oxygen blender ensure stable oxygen concentration.\n" +
                    "• Reliable CPAP control valve and pressure monitoring system improve CPAP adjustment precision.",

            "• 8.4 LCD display\n" +
                    "• Temperature monitoring\n" +
                    "• Temperature servo control\n" +
                    "• Humidity Monitoring\n" +
                    "• Humidity Servo Control\n" +
                    "• Air temperature and body temperature control two modes\n" +
                    "• Mattress tilt ±12 °\n" +
                    "• Ergonomic designed handle with perfect height\n" +
                    "• Trend for 2, 4, 8, 12, 24 hours and up to 7 days\n" +
                    "• Front and rear double-walls and air curtain\n" +
                    "• Drawers Large (28.0cm x 24.7cm x 36.0cm)\n" +
                    "• Drawers Small (28.0cm x 11.3cm x 36.0cm) *2\n" +
                    "• Auxiliary Power Output*3\n" +
                    "• Backup battery to keep air flow and alarm after power failure for more than 30 mins",

            "• Controlled Mechanical Ventilation (CMV), \n" +
                    "• Intermittent Mandatory Ventilation (IMV),\n" +
                    "• Continuous Positive Airway Pressure (CPAP),\n" +
                    "• Manual Ventilation (Manual).\n" +
                    "• 1~120BPM\n" +
                    "• 1:1~1:3\n" +
                    "• 10:1~1:9.9\n" +
                    "• 3 L/min~20 L/min\n" +
                    "• Minimum 10 mL~300 mL\n" +
                    "• Max. Ventilation volume greater than 5 L/min\n" +
                    "• 21%~100%\n" +
                    "• Not greater than 3×10-2 mL/Pa\n" +
                    "• 0kPa~2.0kPa\n" +
                    "• Safety valve shall be opened when the airway pressure is within -300 Pa ~ -800 Pa\n" +
                    "• 1kPa~9kPa\n" +
                    "• 0.2s~3.0s\n" +
                    "• Pressure Trigger -1.0~0kPa (Special Order)\n" +
                    "• Flow Trigger 0~10L (Special Order)\n" +
                    "• Not greater than 10kPa\n" +
                    "• 0kPa~2.0kPa\n" +
                    "• Real-time monitoring within the adjustment range of tidal volume: ＜100 mL: ±10 mL; ≥100 mL: -±15 %;\n" +
                    "• 21%~100%\n" +
                    "• Audible alarm sounds immediately in case of power outage and lasts at least for 120s\n" +
                    "• Audible and visual alarm sounds within 6s after gas source interruption occurs and lasts at least for 120s\n" +
                    "• PEEP +0.2kPa\n" +
                    "• Inspiratory pressure +0.4 kPa",

            "• Size: 810*520*940mm\n" +
                    "• Steel bed frames with basket\n" +
                    "• Steel side rails around\n" +
                    "• ABS tray with mattress\n" +
                    "• 4 castors ",

            "• Size: L*W*D: 180*60*30mm\n" +
                    "• Weight: about 215 grams\n" +
                    "• Display: 2.4 inch TFT color display\n" +
                    "• User input: 5 key input (up, down, left, right, confirmation)\n" +
                    "• Memory: 200 test data for up to 50 subjects\n" +
                    "• Power adapter: DC 5V 1000MA\n" +
                    "• Interface: charging with the base and data communication with the host computer\n" +
                    "• Probe interface: HDMI\n" +
                    "• Probe cable: length 100CM\n" +
                    "• Earplugs: a variety of size silicone earplugs\n" +
                    "• Printer: Standard 58mm Bluetooth thermal ",

            "• The bed surface is powder coated steel, with punching holes.\n" +
                    "• Powder coated steel head & foot board.\n" +
                    "• 5 silent casters ensure the move of beds stable and leave no noise mark on floor.\n" +
                    "• It characterized for safe to use, easy to clean and disinfect.\n" +
                    "• Max loading 150kg\n" +
                    "• Dimension 1600*740*1040m "


    };


    // Laboratory Equipment Data
    public static String[] laboratoryItemsName = new String[]{"Electroly Analyzer", "Biobase Autoclave", "Biobase Bio-safety Cabinet", "Single Channel Pipettors", "Biobase Urine Analyzer",
            "Automatic Chemistry Analyzer", "Binocular Microscope", "Cornley Electrolyte analyzer", "Olympus Biological Microscope"};
    public static int[] laboratoryItemsImage = new int[]{R.drawable.lab_balio_electrolyteanalyzer_ex3041, R.drawable.lab_biobase_autoclave24l_bkm_z24b1, R.drawable.lab_biobase_biosafetycabinet_bsc_4fa22,
            R.drawable.lab_biobase_setofsinglechannelpipettors1, R.drawable.lab_biobase_urineanalyzer_ua_2001, R.drawable.lab_tecom_automaticchemistryanalyzer_tc60302,
            R.drawable.lab_ometop_binocularmicroscope_pb_33202__1, R.drawable.cornley_electroanalyzer2, R.drawable.olympus_biologicalmicroscope_cx4311};
    public  static double[] laboratoryProductPrice = new double[]{210981.73, 121923.87, 10000.00, 2220.00, 38679.18, 444787.33, 27003.17, 205363.26, 36178.63};

    public static String[] laboratoryProductBrand =  new String[]{"MedGroup", "Biobase", "Biobase", "Biobase", "Biobase", "Tecom", "Ometop", "MedGroup", "Olympus"};

    public static String[] laboratoryProductModel =  new String[]{"MSLEA09", "MSLTA01", "BSC700IIA2-Z CLASS II A2",

            "-", "UA 2001", "TC6030", "PB 33202", "MSLEA08", "CX23"};

    public static String[] laboratoryProductDetails =  new String[]{"• Automatic calibration,\n" +
            "• Reagents pack with integrated bin,\n" +
            "• Blood, Serum, Plasma, Urine and other substances,\n" +
            "• Na+, K+, Cl-, Ca++, Li+, pH available.\n" +
            "• Volume : 40 μL,\n" +
            "• Complete Software,\n" +
            "• Memory : 1000 tests.",

            "• Build-in open type water tank\n" +
                    "• The sterilizer adopts easy-clean open type water tank which can support repeated program running if fully injected with water.\n" +
                    "• High-efficiency ultimate vacuum\n" +
                    "• The sterilizer adopts high-efficiency low noise vacuum system which has excellent effects.\n" +
                    "• Large LCD display for BKM-Z18/24B\n" +
                    "• The LCD screen can display temperature, pressure, time, operating status, failure warning and other information.\n" +
                    "• It is convenient for customers to observe the sterilizer running status.\n" +
                    "• Multiple program types\n" +
                    "• The system has various programs that include: packed items, unpacked items, custom program, rapid program, BD testing program, vacuum testing program, preheat program and drying program.\n" +
                    "• Standard USB port for BKM-Z18/24B\n" +
                    "• Users can store the sterilization data with USB disk.\n" +
                    "• Optional mini printer can be attached to record the process of sterilization.",

            "• Large LCD display, all information is displayed.\n" +
                    "• Adjust air speed automatically.\n" +
                    "• With memory function in case of power failure.\n" +
                    "• Two-sided glass window, easy to observe.\n" +
                    "• Unibody design.Work table is made up of 3pcs stainless plate, easy to disassemble and clean\n" +
                    "• Interlock function: UV lamp and front window; UV lamp and blower, fluorescent lamp; blower and front window\n" +
                    "• Front 10’ slanted to offer operator comfort while working for a long time, reduce glare and maximize reach into the work area.\n" +
                    "• Separation design between cabinet and base stand. The height of adjustable base stand can be customized.",

            "• Lightweight, ergonomic, low force design\n" +
                    "• Digital display clearly reads volume setting\n" +
                    "• The pipettes cover volume range of O.1UI t0 10ml\n" +
                    "• Easy to calibrate and maintain with tool supplied\n" +
                    "v Design helps avoid repetitive strain injuries\n" +
                    "• Calibrated in accordance with IS08655.\n" +
                    "• Each pipette supplied with individual test certificate\n" +
                    "• The low part is available for autoclaving",

            "• 120 samples per hour.\n" +
                    "• Sole test and continuous test.\n" +
                    "• Report printing format is optional: “+1- ‘’ or number.\n" +
                    "• Automatically inputs and records the color and clarify of the urine samples.\n" +
                    "• Automatically inputs the date and medical record number, inquire conveniently",

            "• Test mode: Kinetic, End point, Two point, Absorbance\n" +
                    "• Wavelength: 7 standard filters, 340, 405, 492, 510, 546, 578, 630 nm, 1 free position\n" +
                    "• Wavelength precision: \u0081}2 nm, width ≤ 10nm\n" +
                    "• Absorbance range: 0~4.500 Abs\n" +
                    "• Absorbance precision: outside 0.0001 Abs , inside 0.00001 Abs\n" +
                    "• Carry over: < 1%\n" +
                    "• Repeatability CV: < 1%\n" +
                    "• Incubator: 37℃, 30℃, 25℃, Precision : \u0081}0.1℃\n" +
                    "• Sample volume: 0~3000 uL (≥500uL recommend)\n" +
                    "• Data Storage: ≥10000\n" +
                    "• Quality control curve: , SD, CV%, L-J quality control curve for every test item\n" +
                    "• Flow cell: 32 uL quartz glass, 10 mm\n" +
                    "• Lamp: Philips 6V 10W Halogen Lamp with long life\n" +
                    "• Screen: 7 inch color LCD\n" +
                    "• CPU: ARM series high speed micro CPU\n" +
                    "• Printer: Internal thermal sensitive printer\n" +
                    "• Interface: RS-232\n" +
                    "• Work Environment: Temperature 0℃ ~ 40℃, Humidity: ≤80%\n" +
                    "• Power supply: 100~240VAC, 50-60Hz, 100VA\n" +
                    "• Dimension: 340(L)*270(W)*160(H)\n" +
                    "• Weight: 5 kg",

            "• 4 shelves in refrigerator compartment\n" +
                    "• 4 shelves in freezer compartment\n" +
                    "• Four one-way wheels with stabilizing adjustable feet\n" +
                    "• 2 locks with key\n" +
                    "• Power cord with Schüko type plug (or British plug on request)\n" +
                    "• Protection fuses (on power switch and electronic mainboard)\n" +
                    "• RFI filter\n" +
                    "• Password protected power switch and settings\n" +
                    "• Electronic control panel with alarm system\n" +
                    "• Remote contact (dry, volt-free) for alarm\n" +
                    "• Data logger with USB port to download records and data\n" +
                    "• Memory of latest alarms visible on screen\n" +
                    "• Power failure at electricity recovery",

            "• Automatic calibration,\n" +
                    "• Reagents pack with integrated bin,\n" +
                    "• Blood, Serum, Plasma, Urine and other substances,\n" +
                    "• Na+, K+, Cl-, Ca++, Li+, pH available.\n" +
                    "• Volume : 40 μL,\n" +
                    "• Complete Software,\n" +
                    "• Memory : 1000 tests.",

            "• 14 drawers in Scotch Brite stainless steel\n" +
                    "• four unidirectional wheels with stabilizing feet\n" +
                    "• key lock\n" +
                    "• 3x1 separable power cable complete with Schuko plug (on request English plug)\n" +
                    "• electric anti-interference filter placed inside the motherboard\n" +
                    "• protection fuses\n" +
                    "• switching on and off with a password\n" +
                    "• electronic control panel with alarm system\n" +
                    "• contact for remote alarm\n" +
                    "• USB port\n" +
                    "• alarm memory\n" +
                    "• graphical temperature recorder, with weekly cycle, working with 1.5V battery, equipped with 52 disks and 1 pen\n" +
                    "• control unit powered by a 12V buffer battery for temperature display and alarm acquisition in the absence of electricity "
    };

    // Hospital Consumable Data
    public static String[] consumablesItemsName = new String[]{"Digital Thermometer", "Disposable Head Cover", "Disposable Shoe Cover", "Gauze Pad", "Gauze Roll", "Eye Pad Sterile", "N95 Mask", "Nebulizer", "Oxygen Tank", "Face Shield", "Hemodialysis Catheter Kit"};
    public static int[] consumablesItemsImage = new int[]{R.drawable.consumable_digital_thermometer, R.drawable.consumable_disposable_head_cover, R.drawable.consumable_disposable_shoe_cover,
            R.drawable.consumable_gauze_pad, R.drawable.consumable_gauze_roll, R.drawable.consumable_eye_pad_sterile, R.drawable.consumable_n95_mask, R.drawable.consumable_nebulizer, R.drawable.categ_hospitalconsumables, R.drawable.con_face_shield, R.drawable.con_hemodialysis_catherer_kit};
    public  static double[] consumablesProductPrice = new double[]{57.00, 244.00, 49.00, 110.00, 117.00, 299.00, 110.00, 999.00, 50.00, 32, 60};

    public static String[] consumablesProductBrand =  new String[]{"Indoplas", "Iraseth Head Cover", "OREX", "Indoplas", "Indoplas", "ORMED", "AUBERGE", "Surgitech", "JRM", "Shunmei", "-"};

    public static String[] consumablesProductModel =  new String[]{"mc-246", "M7-5", " Disposable Shoe Cover (Non-Woven)", "Indoplas Absorbent Gauze Sponge Pad", "Indoplas Absorbent Gauze Sponge Roll", "ORMED Sterile Eye Pads ",
            "N95 Face Mask 5 Layer Protective Original N95", "Surgitech Heavy Duty Compressor Nebulizer", "JRM Oxygen Tank 15Lbs", "Shunmei Hemodialysis Catheter kits", "Full Face Shield"};


    public static String[] consumablesProductDetails =  new String[]{"• High accuracy" +
            "• Easy to read digital display\n" +
            "• Peak-hold and auto shut-off functions\n" +
            "• Safe and unbreakable\n" +
            "• With beeper alarm\n" +
            "• Takes 60 seconds to measure body temperature.\n" +
            "• Automatic Shut-Off\n" +
            "• Can be used in oral, recta and under armpit.\n" +
            "\n" +
            "Battery: One 1.5 V DC button battery (size LR41 or SR41, UCC 392)",

            "• Material: non-woven\n" +
                    "• Size: 18 inches (Note: 18 inches means maximum stretch length is 46cm)\n" +
                    "• Product single weight: about 2g/pcs\n" +
                    "• Packing size: about 20*17*9cm/7.87*6.69*3.54in\n" +
                    "• Package weight: about 200g\n" +
                    " \n" +
                    "• Packing Included:\n" +
                    "• 100 pcs/bag Disposal Hair Net\"",

            "• Color: Blue\n" +
                    "• Material: Non-Woven / CPE (Plastic) / PE (Plastic)",

            "• Absorbent Gauze Sponge 4x4 NON-STERILE\n" +
                    "\n" +
                    "• 40's x 40's 28 x 24 mesh\n" +
                    "\n" +
                    "• 4\"x4\" - 8PLY\n" +
                    "\n" +
                    "• BOX OF 100",

            "• Type of gauze: 17 threads/cm2 (grammage 23g /m2).\n" +
                    "• Components: Bleached, purified textile, plain weave.\n" +
                    "• Material: Absorbent gauze, 100% cotton.\n" +
                    "• Width: 65 to 90cm.\n" +
                    "• Length:100m.\n" +
                    "• Single use\n" +
                    "• Non-sterile.",

            "• Ormed Sterile Eye Pads (individually packed)\n" +
                    "• FDA Certification: CMDN-2021-00381\n" +
                    "\n" +
                    "• Composed of 70% viscose + 30% polyester fabric\n" +
                    "• Ethylene Oxide Sterilized\n" +
                    "\n" +
                    "Available in: \n" +
                    "• 4 x 6 cm \n",

            "• Series:KN95 Adult Mask\n" +
                    "• Applicable object:adults\n" +
                    "• Number of layers:5 layer protection\n" +
                    "• Shelf life: 24 months\n" +
                    "• Size:11*17 cm (± 0.5cm)\n" +
                    "• Packing quantity:10 pieces/pack\n" +
                    "• Quantity:10pcs/50pcs\n" +
                    "• Color:Black, White\n" +
                    "• Function:protection, decoration, dustproof, anti-droplet, anti-pollen\n" +
                    "• Thickness:0.1mm\n" +
                    "• Material:non-woven fabric\n" +
                    "• Origin (domestic):China\n" +
                    "• Type:Disposable mask\n" +
                    "• Breathing valve device:No\n" +
                    "• Mask style:hanging ear type\n" +
                    "• Execution standard number:GB/T2626-2006\n" +
                    "• Product Certification:Yes\n" +
                    "• Product Type:Mask\n" +
                    "• Folding method:folded\n" +
                    "• Nose clip:nose bridge\n" +
                    "• Protection level:AAAA\n" +
                    "• Filtering effect:99%",

            "• Brand\n" +
                    "• SURGITECH\n" +
                    "• Shelf Life\n" +
                    "• 12 Months\n" +
                    "• Pack Type\n" +
                    "• Value Size\n" +
                    "• Warranty Type\n" +
                    "• Supplier Warranty\n" +
                    "• Weight\n" +
                    "• 1.76kg\n" +
                    "• Pack Size\n" +
                    "• 1.76KG\n" +
                    "• Quantity\n" +
                    "• 100\n" +
                    "• FDA Registration No.\n" +
                    "• 0463358",

            "• Straight thread design eliminates thread damage.\n" +
                    "• Safety paint protects cylinder sidewall & provides heat detection.",

            "• Double Lumen 11 5fr x 15cm CURVED\n" +
                    "• 1 pc. 0.38x 70cm j/fle x nitinol guidewires\n" +
                    "• 1 pc catheter\n" +
                    "• 2-10f + 12f dolators\n" +
                    "• 1 scalpel\n" +
                    "• 1 guidewire advancer\n" +
                    "• 2 injection caps\n" +
                    "• 1 18 g needle\n" +
                    "• 1 5ml syrienge",

            "• Size: 28 * 21 cm\n" +
                    "• Material: PC / APET\n" +
                    "• Color: Transparent\n" +
                    "• Remove the blue protective film before use."





    };




}