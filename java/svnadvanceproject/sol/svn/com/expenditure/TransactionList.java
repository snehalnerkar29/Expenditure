package svnadvanceproject.sol.svn.com.expenditure;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Naresh Nerkar on 25-09-2018.
 */

public class TransactionList extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltransaction_list);


    }
}
