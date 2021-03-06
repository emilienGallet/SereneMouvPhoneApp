package zemoov.serenemouv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.internal.zzab;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.internal.zzaf;
import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzal;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzar;
import com.google.android.gms.maps.internal.zzat;
import com.google.android.gms.maps.internal.zzav;
import com.google.android.gms.maps.internal.zzax;
import com.google.android.gms.maps.internal.zzaz;
import com.google.android.gms.maps.internal.zzbb;
import com.google.android.gms.maps.internal.zzbd;
import com.google.android.gms.maps.internal.zzbf;
import com.google.android.gms.maps.internal.zzbs;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.internal.zzl;
import com.google.android.gms.maps.internal.zzp;
import com.google.android.gms.maps.internal.zzr;
import com.google.android.gms.maps.internal.zzv;
import com.google.android.gms.maps.internal.zzx;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    CustomExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<GoogleMap>> expandableListDetail;
    TextView textView;
    private ImageButton btnJour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        expandableListView = (ExpandableListView) findViewById(R.id.listtrajet);
        //expandableListDetail = ExpandableListDataPump.getData();
      //  expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListDetail = new HashMap<>();
        expandableListTitle =  new ArrayList<>();
        List<GoogleMap> cricket = new ArrayList<GoogleMap>();
        GoogleMap map = new GoogleMap(new IGoogleMapDelegate() {
            @Override
            public CameraPosition getCameraPosition() throws RemoteException {

            return null;
            }

            @Override
            public float getMaxZoomLevel() throws RemoteException {
                return 0;
            }

            @Override
            public float getMinZoomLevel() throws RemoteException {
                return 0;
            }

            @Override
            public void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {

            }

            @Override
            public void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {

            }

            @Override
            public void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzc) throws RemoteException {

            }

            @Override
            public void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzc) throws RemoteException {

            }

            @Override
            public void stopAnimation() throws RemoteException {

            }

            @Override
            public zzz addPolyline(PolylineOptions polylineOptions) throws RemoteException {
                return null;
            }

            @Override
            public zzw addPolygon(PolygonOptions polygonOptions) throws RemoteException {
                return null;
            }

            @Override
            public zzt addMarker(MarkerOptions markerOptions) throws RemoteException {
                return null;
            }

            @Override
            public zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
                return null;
            }

            @Override
            public zzac addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
                return null;
            }

            @Override
            public void clear() throws RemoteException {

            }

            @Override
            public int getMapType() throws RemoteException {
                return 0;
            }

            @Override
            public void setMapType(int i) throws RemoteException {

            }

            @Override
            public boolean isTrafficEnabled() throws RemoteException {
                return false;
            }

            @Override
            public void setTrafficEnabled(boolean b) throws RemoteException {

            }

            @Override
            public boolean isIndoorEnabled() throws RemoteException {
                return false;
            }

            @Override
            public boolean setIndoorEnabled(boolean b) throws RemoteException {
                return false;
            }

            @Override
            public boolean isMyLocationEnabled() throws RemoteException {
                return false;
            }

            @Override
            public void setMyLocationEnabled(boolean b) throws RemoteException {

            }

            @Override
            public Location getMyLocation() throws RemoteException {
                return null;
            }

            @Override
            public void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {

            }

            @Override
            public IUiSettingsDelegate getUiSettings() throws RemoteException {
                return null;
            }

            @Override
            public IProjectionDelegate getProjection() throws RemoteException {
                return null;
            }

            @Override
            public void setOnCameraChangeListener(zzl zzl) throws RemoteException {

            }

            @Override
            public void setOnMapClickListener(zzaj zzaj) throws RemoteException {

            }

            @Override
            public void setOnMapLongClickListener(zzan zzan) throws RemoteException {

            }

            @Override
            public void setOnMarkerClickListener(zzar zzar) throws RemoteException {

            }

            @Override
            public void setOnMarkerDragListener(zzat zzat) throws RemoteException {

            }

            @Override
            public void setOnInfoWindowClickListener(zzab zzab) throws RemoteException {

            }

            @Override
            public void setInfoWindowAdapter(zzh zzh) throws RemoteException {

            }

            @Override
            public com.google.android.gms.internal.maps.zzh addCircle(CircleOptions circleOptions) throws RemoteException {
                return null;
            }

            @Override
            public void setOnMyLocationChangeListener(zzax zzax) throws RemoteException {

            }

            @Override
            public void setOnMyLocationButtonClickListener(zzav zzav) throws RemoteException {

            }

            @Override
            public void snapshot(zzbs zzbs, IObjectWrapper iObjectWrapper) throws RemoteException {

            }

            @Override
            public void setPadding(int i, int i1, int i2, int i3) throws RemoteException {

            }

            @Override
            public boolean isBuildingsEnabled() throws RemoteException {
                return false;
            }

            @Override
            public void setBuildingsEnabled(boolean b) throws RemoteException {

            }

            @Override
            public void setOnMapLoadedCallback(zzal zzal) throws RemoteException {

            }

            @Override
            public zzn getFocusedBuilding() throws RemoteException {
                return null;
            }

            @Override
            public void setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.zzz zzz) throws RemoteException {

            }

            @Override
            public void setWatermarkEnabled(boolean b) throws RemoteException {

            }

            @Override
            public void getMapAsync(zzap zzap) throws RemoteException {

            }

            @Override
            public void onCreate(Bundle bundle) throws RemoteException {

            }

            @Override
            public void onResume() throws RemoteException {

            }

            @Override
            public void onPause() throws RemoteException {

            }

            @Override
            public void onDestroy() throws RemoteException {

            }

            @Override
            public void onLowMemory() throws RemoteException {

            }

            @Override
            public boolean useViewLifecycleWhenInFragment() throws RemoteException {
                return false;
            }

            @Override
            public void onSaveInstanceState(Bundle bundle) throws RemoteException {

            }

            @Override
            public void setContentDescription(String s) throws RemoteException {

            }

            @Override
            public void snapshotForTest(zzbs zzbs) throws RemoteException {

            }

            @Override
            public void setOnPoiClickListener(zzbb zzbb) throws RemoteException {

            }

            @Override
            public void onEnterAmbient(Bundle bundle) throws RemoteException {

            }

            @Override
            public void onExitAmbient() throws RemoteException {

            }

            @Override
            public void setOnGroundOverlayClickListener(zzx zzx) throws RemoteException {

            }

            @Override
            public void setOnInfoWindowLongClickListener(zzaf zzaf) throws RemoteException {

            }

            @Override
            public void setOnPolygonClickListener(zzbd zzbd) throws RemoteException {

            }

            @Override
            public void setOnInfoWindowCloseListener(zzad zzad) throws RemoteException {

            }

            @Override
            public void setOnPolylineClickListener(zzbf zzbf) throws RemoteException {

            }

            @Override
            public void setOnCircleClickListener(zzv zzv) throws RemoteException {

            }

            @Override
            public void setMinZoomPreference(float v) throws RemoteException {

            }

            @Override
            public void setMaxZoomPreference(float v) throws RemoteException {

            }

            @Override
            public void resetMinMaxZoomPreference() throws RemoteException {

            }

            @Override
            public void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {

            }

            @Override
            public void setOnCameraMoveStartedListener(com.google.android.gms.maps.internal.zzt zzt) throws RemoteException {

            }

            @Override
            public void setOnCameraMoveListener(zzr zzr) throws RemoteException {

            }

            @Override
            public void setOnCameraMoveCanceledListener(zzp zzp) throws RemoteException {

            }

            @Override
            public void setOnCameraIdleListener(com.google.android.gms.maps.internal.zzn zzn) throws RemoteException {

            }

            @Override
            public boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
                return false;
            }

            @Override
            public void onStart() throws RemoteException {

            }

            @Override
            public void onStop() throws RemoteException {

            }

            @Override
            public void setOnMyLocationClickListener(zzaz zzaz) throws RemoteException {

            }

            @Override
            public IBinder asBinder() {
                return null;
            }
        });
        cricket.add(map);

        expandableListDetail.put("Saint Etienne", cricket);
        expandableListDetail.put("Lyon", cricket);

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        textView = (TextView)findViewById(R.id.historiqueVide);
        if(expandableListTitle.size() ==0){
            textView.setVisibility(View.VISIBLE);
        }

        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
        btnJour = (ImageButton) findViewById(R.id.jour);

        btnJour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                recreate();
                //     setTheme(darkTheme ? R.style.AppThemeDark : R.style.AppThemeLight);
            }
        });
    }
}