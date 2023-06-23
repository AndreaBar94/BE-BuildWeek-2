const Profile = () => {
  return (
    <>
      <div className="container">
        <div className="text-center ms-3  mb-3 profile">That's your Profile</div>

        <div placeholder="Your surname" className="d-flex miniCont">
          <div className="text-center ms-3 text">Surname:</div>
          <input className="inputText " type="text" />
        </div>
        <div placeholder="Your name" className="d-flex miniCont">
          <div className="text-center ms-3 text">Name:</div>
          <input className="inputText " type="text" />
        </div>
        <div placeholder="Your email" className="d-flex miniCont">
          <div className="text-center ms-3 text">Email or username</div>
          <input className="inputText " type="email" />
        </div>
        <div placeholder="Your phone number" className="d-flex miniCont">
          <div className="text-center ms-3 text">Phone number</div>
          <input className="inputText " type="tel" />
        </div>
      </div>
    </>
  );
};
export default Profile;
