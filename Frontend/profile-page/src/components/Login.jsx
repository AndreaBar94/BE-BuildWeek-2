import InputGroup from 'react-bootstrap/InputGroup';

const Login = () => {
    return(
        <>
        <div className="head-style">
                </div>
    <div className="center">
        <p className="cont">To continue, log-in</p>
    </div>
    <div className="center">
        <form action="#">
            <div className="input-grid">
                <label for="email" className="form-label">Email address or Username</label>
                <InputGroup type="text" className="input" placeholder="Email address or Username" required></InputGroup>

                <label for="pass" className="form-label">Password</label>
                <InputGroup type="password" className="input" id="pass" required></InputGroup>
            </div>
            <div className="form-down">
                <div className="for-check">
                    <p>Forgot Password?</p>
                </div>

                <button>Sign In</button>
            </div>
        </form>
    </div> 
        </>
    );
}

export default Login;