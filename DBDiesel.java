package com.example.fueldelivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.maps.model.LatLng;

public class DBDiesel extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "order_diesel.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ORDER = "orders";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CAR_NAME = "car_name";
    private static final String COLUMN_CAR_MODEL = "car_model";
    private static final String COLUMN_PLATE_NUMBER = "plate_number";
    private static final String COLUMN_PLATE_LETTER = "plate_letter";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PAYMENT_TYPE = "payment_type";
    private static final String COLUMN_LOCATION_LATITUDE = "location_latitude";
    private static final String COLUMN_LOCATION_LONGITUDE = "location_longitude";

    public DBDiesel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_ORDER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CAR_NAME + " TEXT, "
                + COLUMN_CAR_MODEL + " TEXT, "
                + COLUMN_PLATE_NUMBER + " TEXT, "
                + COLUMN_PLATE_LETTER + " TEXT, "
                + COLUMN_QUANTITY + " TEXT, "
                + COLUMN_PAYMENT_TYPE + " INTEGER, "
                + COLUMN_LOCATION_LATITUDE + " REAL, "
                + COLUMN_LOCATION_LONGITUDE + " REAL"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }

    public long insertOrder(String carName, String carModel, String plateNumber, String plateLetter, String quantity, boolean isOnlinePayment, LatLng location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAR_NAME, carName);
        values.put(COLUMN_CAR_MODEL, carModel);
        values.put(COLUMN_PLATE_NUMBER, plateNumber);
        values.put(COLUMN_PLATE_LETTER, plateLetter);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_PAYMENT_TYPE, isOnlinePayment ? 1 : 0);
        values.put(COLUMN_LOCATION_LATITUDE, location.latitude);
        values.put(COLUMN_LOCATION_LONGITUDE, location.longitude);
        long orderId = db.insert(TABLE_ORDER, null, values);
        db.close();
        return orderId;
    }
}
