sap.ui.define([ "sap/ui/core/mvc/Controller", "sap/ui/model/json/JSONModel" ],
		function(Controller, JSONModel) {

			/**
			 * Controller for the Employee view.
			 * 
			 * @class
			 * @name msg.demo.Employee
			 */
			return Controller.extend("msg.demo.Employee", {

				/**
				 * Lifecycle hook. Builds the view models.
				 * 
				 * @method msg.demo.Employee#onInit
				 */
				onInit : function() {
					this.getView().setModel(new JSONModel([]));
					this.getView().setModel(new JSONModel({}), "dialog");
				},

				/**
				 * Formats the role enum to a user-suitable text.
				 * 
				 * @method msg.demo.Employee#formatRole
				 * @param {string}
				 *            sRole The role enum value.
				 * @returns {string} A text suitable for being displayed.
				 */
				formatRole : function(sRole) {
					switch (sRole) {
					case "CONSULTANT":
						return "Consultant";
					case "MANAGER":
						return "Manager";
					case "SPECIALIST":
						return "Specialist";
					default:
						return "Unknown";
					}
				},

				/**
				 * Refreshes the model contents by reading the employee list.
				 * 
				 * @method msg.demo.Employee#onRefresh
				 */
				onRefresh : function() {
					this.getView().getModel().refresh();
				},

				/**
				 * Initializes the dialog model contents and opens the dialog.
				 * 
				 * @method msg.demo.Employee#onAdd
				 */
				onAdd : function() {
					this.getView().getModel("dialog").setData({
						name : "",
						role : null,
						hiredOn : ""
					});
					this.byId("dialog").open();
				},

				/**
				 * Simply closes the dialog.
				 * 
				 * @method msg.demo.Employee#onCancelAdd
				 */
				onCancelAdd : function() {
					this.byId("dialog").close();
				},

				/**
				 * Creates the employee by calling the service. If the call is
				 * successful, it closes the dialog and refreshes the model.
				 * 
				 * @method msg.demo.Employee#onConfirmAdd
				 */
				onConfirmAdd : function() {
					var oModel = this.getView().getModel(),
						aData = (oModel.getProperty("/") || []),
						oEmployee = this.getView().getModel("dialog").getData();
					aData.push(oEmployee);
					oModel.setData(aData);
					this.byId("dialog").close();
				}

			});

		});