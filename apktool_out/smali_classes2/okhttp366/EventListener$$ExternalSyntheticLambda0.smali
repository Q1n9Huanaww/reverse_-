.class public final synthetic Lokhttp366/EventListener$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "D8$$SyntheticClass"

# interfaces
.implements Lokhttp366/EventListener$Factory;


# instance fields
.field public final synthetic f$0:Lokhttp366/EventListener;


# direct methods
.method public synthetic constructor <init>(Lokhttp366/EventListener;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lokhttp366/EventListener$$ExternalSyntheticLambda0;->f$0:Lokhttp366/EventListener;

    return-void
.end method


# virtual methods
.method public final create(Lokhttp366/Call;)Lokhttp366/EventListener;
    .locals 1

    iget-object v0, p0, Lokhttp366/EventListener$$ExternalSyntheticLambda0;->f$0:Lokhttp366/EventListener;

    invoke-static {v0, p1}, Lokhttp366/EventListener;->lambda$factory$0(Lokhttp366/EventListener;Lokhttp366/Call;)Lokhttp366/EventListener;

    move-result-object p1

    return-object p1
.end method
