package com.example.persistentbottomsheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    Button buttonSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingButton);
        buttonSheet = findViewById(R.id.buttonBottomSheetPersistent);
        // получение вью нижнего экрана
        ConstraintLayout bottomSheet = findViewById(R.id.bottomSheet);//айдишник из БОТОМШИТ лейаута

        // настройка поведения нижнего экрана
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        // настройка максимальной высоты. Это все настройки того, как быдет вью выглядет по дефолту.
        //Или спрятан, или открыт и прочее
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        //bottomSheetBehavior.setState(BottomSheetBehavior.STATE_SETTLING);

        //TODO ярко, чтоб видно было. Выноска из статьи, по состояниям, что были выше.
        // To control the sliding and collapsing of the dialog, we use states.
        // The Bottom Sheet has several states which you need to understand.
        // They include:STATE_EXPANDED - the dialog is visible to its maximum defined height.
        // STATE_COLLAPSED - the dialog is visible depending on the set peekHeight.
        // STATE_DRAGGING - the user is dragging the dialog up and down.
        // STATE_SETTLING - show that the dialog is settling at a specific height.
        // This can be the peekHeight, expanded height, or zero if the dialog is hidden
        // STATE_HIDDEN - the dialog is not visible.

        // настройка максимальной высоты
        bottomSheetBehavior.setPeekHeight(50);

        // настройка возможности скрыть элемент при свайпе вниз
        bottomSheetBehavior.setHideable(false);

        //Это все были настройки. Только ЗАПОМНИ, что у них приоритет ВЫШЕ, чем у лэйатеров.
        //Если ты в боттомшиет поставиш хедбл true, а здесь фолзе, то будет именно FALSE.

        // настройка колбэков при изменениях
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                floatingActionButton.animate().scaleX(1 - slideOffset).scaleY(1 - slideOffset).setDuration(0).start();
            }
        });

        bottomSheetBehavior.removeBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        //Вот с этой херней про ремув не понял. Судя по девелоперу Андроде, то может удалять последние измененя. А как - гадай
        // В статье другой ОБЩИЙ метод, но он деприкате, так что юзаем эти. Add именно как тот, что деприкате.

        //Пойми глав разницу. Есть перситент ботом шит - это окошечко, которое, не затеняет основной экран.
        // То бишь этот проект. А есть модал - песня другая. Там затенение.
        // Мне больше нравится. НО и МОДАЛ БОТТОМ ШИТ  делится на диалоговое окно и фрагмент.
        // В диалоговом типе возможностей по больше со строчками, что будут у тебя в перечне (пример с тостами).
        // А в фрагменте просто тапать можно

        //ссылочки: https://www.section.io/engineering-education/bottom-sheet-dialogs-using-android-studio/
        // https://www.simplifiedcoding.net/bottom-sheet-android/#1-Persistent-Bottom-Sheet
    }
}