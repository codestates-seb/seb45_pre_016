/* eslint-disable react/prop-types */
/* eslint-disable react/react-in-jsx-scope */
import { Modal } from "../tokens/Style";
import { useNavigate } from "react-router-dom";

const ModalBasic = ({ setModalOpen }) => {

  const navigator = useNavigate();

  const resetQuestion = () => {
  navigator('/');
  localStorage.removeItem('title');
  localStorage.removeItem('content')
  }

  const closeModal = (e) => {
    if (
      e.target.className === "close-back" ||
      e.target.className === "close-btn" ||
      e.target.className === "cancel"
    ) {
      setModalOpen(false);
    }
  };

  return (
    <>
      <Modal>
        <div className="close-back" onClick={closeModal}></div>
        <div className="content">
          <div className="flex">
            <h1>Discard question</h1>
            <button className="close-btn" onClick={closeModal}>
              X
            </button>
          </div>
          <p>
            Are you sure you want to discard this question? This cannot be
            undone.
          </p>
          <div className="flex">
            <div className="discardq" onClick={resetQuestion}>Discard question</div>
            <div onClick={closeModal} className="cancel">
              Cancel
            </div>
          </div>
        </div>
      </Modal>
    </>
  );
};

export default ModalBasic;
