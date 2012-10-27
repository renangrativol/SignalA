package com.zsoft.SignalA.Transport;

import java.util.concurrent.atomic.AtomicBoolean;

import com.zsoft.SignalA.Connection;
import com.zsoft.SignalA.ConnectionState;
import com.zsoft.SignalA.SendCallback;

public abstract class StateBase {
    protected Connection mConnection;
    protected AtomicBoolean mIsRunning = new AtomicBoolean(false);
    
    public StateBase(Connection connection)
    {
        mConnection = connection;
    }

    public abstract ConnectionState getState();
    public abstract void Start();
    public abstract void Stop();

    public boolean getIsRunning() { return mIsRunning.get(); }
    public void Run()
    {
    	if (mIsRunning.compareAndSet(false, true)) {
            try
            {
                OnRun();
            }
            finally
            {
                mIsRunning.set(false);
            }
    	}
    }

    protected abstract void OnRun();

	public abstract void Send(CharSequence text, SendCallback callback);
}
