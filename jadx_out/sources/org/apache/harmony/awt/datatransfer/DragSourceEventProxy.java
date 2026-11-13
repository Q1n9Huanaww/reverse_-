package org.apache.harmony.awt.datatransfer;

import java.awt.Point;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;

/* JADX INFO: loaded from: classes.dex */
public class DragSourceEventProxy implements Runnable {
    public static final int DRAG_ACTION_CHANGED = 3;
    public static final int DRAG_DROP_END = 6;
    public static final int DRAG_ENTER = 1;
    public static final int DRAG_EXIT = 5;
    public static final int DRAG_MOUSE_MOVED = 4;
    public static final int DRAG_OVER = 2;
    private final DragSourceContext context;
    private final int modifiers;
    private final boolean success;
    private final int targetActions;
    private final int type;
    private final int userAction;

    /* JADX INFO: renamed from: x */
    private final int f5064x;

    /* JADX INFO: renamed from: y */
    private final int f5065y;

    public DragSourceEventProxy(DragSourceContext dragSourceContext, int i, int i2, int i3, Point point, int i4) {
        this.context = dragSourceContext;
        this.type = i;
        this.userAction = i2;
        this.targetActions = i3;
        this.f5064x = point.x;
        this.f5065y = point.y;
        this.modifiers = i4;
        this.success = false;
    }

    public DragSourceEventProxy(DragSourceContext dragSourceContext, int i, int i2, boolean z, Point point, int i3) {
        this.context = dragSourceContext;
        this.type = i;
        this.userAction = i2;
        this.targetActions = i2;
        this.f5064x = point.x;
        this.f5065y = point.y;
        this.modifiers = i3;
        this.success = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        switch (this.type) {
            case 1:
                this.context.dragEnter(newDragSourceDragEvent());
                break;
            case 2:
                this.context.dragOver(newDragSourceDragEvent());
                break;
            case 3:
                this.context.dropActionChanged(newDragSourceDragEvent());
                break;
            case 4:
                this.context.dragMouseMoved(newDragSourceDragEvent());
                break;
            case 5:
                this.context.dragExit(new DragSourceEvent(this.context, this.f5064x, this.f5065y));
                break;
            case 6:
                this.context.dragExit(new DragSourceDropEvent(this.context, this.userAction, this.success, this.f5064x, this.f5065y));
                break;
        }
    }

    private DragSourceDragEvent newDragSourceDragEvent() {
        return new DragSourceDragEvent(this.context, this.userAction, this.targetActions, this.modifiers, this.f5064x, this.f5065y);
    }
}
